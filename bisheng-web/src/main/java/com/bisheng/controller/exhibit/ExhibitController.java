package com.bisheng.controller.exhibit;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.apps.system.business.AreaBusiness;
import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.DateUtil;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.enums.ExhibitStatusEnum;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.system.enums.AreaLevelEnum;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.util.ExcelManager;
import com.bisheng.util.LogUtil;
import com.bisheng.util.exportModel.ExcelExhibitExportModel;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**
 * 展馆控制类
 * @author lihao
 */
@Controller
@RequestMapping("exhibit")
public class ExhibitController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(ExhibitController.class);

	@Autowired
    private ExhibitBusiness exhibitBusiness;
	@Autowired
    private AreaBusiness areaBusiness;
    
    private Gson gson = new Gson();
    
    @RequestMapping("/toExhibit")
    public ModelAndView toExhibit() {
    	ModelAndView mav = new ModelAndView("exhibit/exhibit/exhibit");
    	mav.addObject("exhibitStatusEnum",EnumJsonConverter.buildEnumJson(ExhibitStatusEnum.class));
        return mav;
    }
    @RequestMapping("/toAddExhibit")
    public ModelAndView toAddExhibit() {
    	ModelAndView mav = new ModelAndView("exhibit/exhibit/addExhibit");
        return getMavContainDict(mav);
    }
    @RequestMapping("/toUpdateExhibit")
    public ModelAndView toUpdateExhibit(String exhibitId) {
    	ModelAndView mav = new ModelAndView("exhibit/exhibit/updateExhibit");
    	ExhibitQueryParam param = new ExhibitQueryParam();
    	param.setExhibitId(Long.valueOf(exhibitId));
    	List<ExhibitModel> exhibitList = exhibitBusiness.queryExhibitListByParam(param);
    	mav.addObject("exhibit", exhibitList.get(0));
        return getMavContainDict(mav);
    }
    /**
 	 *  往ModelAndView中,装入字典项
 	 */
     private ModelAndView getMavContainDict (ModelAndView mav) {
     	// 获取省级地区列表
     	AreaParam param = new AreaParam();
     	param.setLevel(AreaLevelEnum.PROVINCE.getKey());
     	List<AreaModel> provinces = areaBusiness.getAreaListByParam(param);
     	mav.addObject("provinces", provinces);
     	mav.addObject("exhibitStatusEnum",EnumJsonConverter.buildEnumJson(ExhibitStatusEnum.class));
     	return mav;
     }   
     
    /**
     * 获取展馆列表,只能看到有对应权限的展馆
     */
    @RequestMapping("/getExhibitList")
    public void getExhibitList(HttpServletResponse response, ExhibitQueryParam param) {
    	logger.info("【展馆管理】查询展馆_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			convertParam(param);
			PageInfo<ExhibitModel> pageInfo = exhibitBusiness.queryPagedExhibitListByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<ExhibitModel>> result = PaginationResult.newInstance(pageInfo.getList());
			logger.debug("【展馆管理】获取展馆列表长度为:" + total);
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.debug("【展馆管理】查询展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【展馆管理】查询展馆_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
    }
    
    /**
	 * 新增展馆 保存
	 */
	@RequestMapping("/addSaveExhibit")
	@ResponseBody
	public Object addSaveExhibit(ExhibitQueryParam param) {
		logger.info("【展馆管理】新增展馆_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			param.setExhibitStatus(ExhibitStatusEnum.REST.getKey());

			List<ExhibitModel> checkList = exhibitBusiness.checkExhibitByParam(param);
			if (checkList != null && checkList.size() > 0) {
				return new ALMResponse(RetCode.FAILURE, "展馆名或者展馆code已存在，请检查输入！");
			}
			param.setCreateByUser(getCurrentUserName());
			param.setUpdateByUser(getCurrentUserName());
			exhibitBusiness.addExhibit(param);
			logger.info("【展馆管理】新增展馆,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("【展馆管理】新增展馆_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("【展馆管理】新增展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
    /**
	 * 修改展馆 保存
	 */
	@RequestMapping("/updateSaveExhibit")
	@ResponseBody
	public Object updateSaveExhibit(ExhibitQueryParam param) {
		logger.info("【展馆管理】修改展馆_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			List<ExhibitModel> checkList = exhibitBusiness.checkExhibitByParam(param);
			if (checkList != null && checkList.size() >= 1) {
				if (checkList.size() > 1) {
					return new ALMResponse(RetCode.FAILURE, "展馆名或者展馆code已存在，请检查输入！");
				} else {
					if (!checkList.get(0).getExhibitId().equals(param.getExhibitId())) {
						return new ALMResponse(RetCode.FAILURE, "展馆名或者展馆code已存在，请检查输入！");
					}
				}
			}
			param.setUpdateByUser(getCurrentUserName());
			exhibitBusiness.updateExhibit(param);
			logger.info("【展馆管理】修改展馆,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("【展馆管理】修改展馆_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("【展馆管理】修改展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}

	/**
	 * 删除展馆
	 * 需要管理员角色
	 */
	@RequestMapping("/deleteExhibit")
	@ResponseBody
	@RequiresRoles({"管理员"})
	public Object deleteExhibit(ExhibitQueryParam param) {
		logger.info("【展馆管理】删除展馆_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			if (null == param.getExhibitId()) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("你好,不能删除展馆");
				return res;
			}
			exhibitBusiness.deleteExhibit(param);
			logger.info("【展馆管理】删除展馆,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("【展馆管理】删除展馆_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("【展馆管理】删除展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}

	/**
	 * 导出数据
	 */
	@RequestMapping("/exportExhibit")
	public void exportExhibit(HttpServletRequest request, HttpServletResponse response, ExhibitQueryParam param){
		logger.info("【展馆管理】导出数据_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		OutputStream os = null;
		try {
			EXPORT_NAME = DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_展馆明细.xlsx";
			param.setUserId(LogUtil.getCurrentUserId());
			List<ExhibitModel> exhibitModelList = exhibitBusiness.queryExhibitListByParam(param);
			processResponse(response);
			List<ExcelExhibitExportModel> exportExhibit = new ArrayList<ExcelExhibitExportModel>();
			if (null != exhibitModelList && !exhibitModelList.isEmpty()) {
				ExcelExhibitExportModel model;
				for (ExhibitModel exhibit : exhibitModelList) {
					model = new ExcelExhibitExportModel();
					model.setExhibitId(exhibit.getExhibitId());
					model.setExhibitName(exhibit.getExhibitName());
					model.setExhibitCode(exhibit.getExhibitCode());
					model.setAreaName(exhibit.getAreaName());
					model.setAddress(exhibit.getAddress());
					model.setPhone(exhibit.getPhone());
					model.setStatus(ExhibitStatusEnum.getDescName(exhibit.getStatus()));
					model.setStartTime(exhibit.getStartTime());
					model.setEndTime(exhibit.getEndTime());
					exportExhibit.add(model);
				}
			}
			logger.info("【展馆管理】导出数据长度:"+exportExhibit.size()+",操作人："+LogUtil.getCurrentUserName());
			os = new BufferedOutputStream(response.getOutputStream());
			ExcelManager.exportExcelByMould(os, 2, exportExhibit, "ExhibitExportExcel", ExcelExhibitExportModel.class);
			os.flush();
		} catch (Exception e) {
			logger.error("【展馆管理】导出数据_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		} finally {
			IOUtils.closeQuietly(os);// 关闭流
		}
	}
}
