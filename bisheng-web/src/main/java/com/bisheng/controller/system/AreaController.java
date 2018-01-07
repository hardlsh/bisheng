package com.bisheng.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisheng.apps.system.business.AreaBusiness;
import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.controller.BaseController;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.bisheng.vo.ViewResult;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;


@Controller
@RequestMapping("area")
public class AreaController extends BaseController{
	// 创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AreaBusiness areaBusiness;
	
	@RequestMapping("/toArea")
	public ModelAndView toArea(){
		ModelAndView mav = new ModelAndView("system/area/areaList");
		return mav;
	}
	
    /**
     * 查询所有地区列表
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/getAllAreaList")
    public void getAllAreaList(HttpServletResponse response) {
		logger.info("[地区管理]查询所有地区_开始,操作人:"+LogUtil.getCurrentUserName());
        try {
            List<Area> areaList = areaBusiness.getAllAreaList();
			String result = new ViewResult(areaList).code(1).msg("获取地区列表成功").json();
            actionResult4Json(result, response);
            logger.info("[地区管理]查询所有地区_成功");
        } catch (Exception e) {
            logger.info("[地区管理]查询所有地区_异常，异常信息为:" + e.getMessage());
            String result = new ViewResult(null).code(-1).msg(e.getMessage()).json();
            actionResult4Json(result, response);
        }
    }
	
	/**
     * 新增地区
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/insertArea")
    public void insertArea(Area area, HttpServletResponse response) {
    	logger.info("[地区管理]新增地区_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(area));
        try {
        	checkParams(area);// 检验数据
        	areaBusiness.insertArea(area);
            String result = new ViewResult(null).code(1).msg("新增地区,保存成功").json();
            actionResult4Json(result, response);
        } catch (Exception e) {
            logger.info("[地区管理]新增地区_异常,异常信息为:" + e.getMessage());
            String result = new ViewResult(null).code(-1).msg(e.getMessage()).json();
            actionResult4Json(result, response);
        }
    }
    
    /**
     * 修改地区
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/updateArea")
    public void updateArea(Area area, HttpServletResponse response) {
    	logger.info("[地区管理]修改地区_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(area));
        try {
            checkParams(area);//检验数据
        	areaBusiness.updateArea(area);
            String result = new ViewResult(null).code(1).msg("修改地区成功").json();
            actionResult4Json(result, response);
        } catch (Exception e) {
            logger.info("[地区管理]修改地区_异常,异常信息为:" + e.getMessage());
            String result = new ViewResult(null).code(-1).msg(e.getMessage()).json();
            actionResult4Json(result, response);
        }
    }
    
    /**
     * 删除地区
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/deleteArea")
    public void deleteArea(Area area, HttpServletResponse response) {
    	logger.info("[地区管理]删除地区_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(area));
        try {
            //检验数据
        	Preconditions.checkArgument(StringUtils.isNotBlank(area.getAreaId()), "地区ID不能为空");
        	areaBusiness.deleteArea(area);
            String result = new ViewResult(null).code(1).msg("删除地区成功").json();
            actionResult4Json(result, response);
        } catch (Exception e) {
            logger.info("[地区管理]删除地区_异常,异常信息为:" + e.getMessage());
            String result = new ViewResult(null).code(-1).msg(e.getMessage()).json();
            actionResult4Json(result, response);
        }
    }
    
    /**
     * 查询省级地区对应的城市
     */
    @RequestMapping("/queryCityArea")
    @ResponseBody
    public Object queryCityArea(AreaParam param, HttpServletResponse response) {
    	logger.info("[地区管理]查询城市地区_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
    	ALMResponse res = null;
        try {
        	List<AreaModel> list = areaBusiness.getAreaListByParam(param);
        	if (list != null && list.size() > 0) {
                res = new ALMResponse(RetCode.SUCCESS);
                res.setData(list);
            } else {
                res = new ALMResponse(RetCode.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("[地区管理]查询城市地区_异常,异常信息为:" + e.getMessage());
            res = new ALMResponse(RetCode.FAILURE);
        }
        return res;
    }
    
    /**
     * 校验参数
     */
    private void checkParams(Area area) {
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getAreaId()), "地区ID不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getAreaName()), "地区名称不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getParentId()), "父级ID不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getShortName()), "地区简称不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getPinyin()), "地区拼音不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getJianpin()), "地区简拼不能为空");
    	Preconditions.checkArgument(StringUtils.isNotBlank(area.getFirstChar()), "地区首字母不能为空");
    }
}
