package com.bisheng.controller.system;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.bisheng.apps.system.business.ResourceBusiness;
import com.bisheng.apps.system.business.RoleBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.generated.Role;
import com.bisheng.services.system.vo.ResourceVo;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private ResourceBusiness resourceBusiness;
    @Autowired
    private RoleBusiness roleBusiness;
    
    private Gson gson = new Gson();
    
    @RequestMapping("/toRole")
    public ModelAndView toRole() {
    	ModelAndView mav = new ModelAndView("system/role/role");
    	mav.addObject("availStatusEnum", EnumJsonConverter.buildEnumJson(AvailStatusEnum.class));
    	
        return mav;
    }
    
	@RequestMapping("/toUpdateRole")
	public ModelAndView toUpdateRole(AuthParam param) {
		ModelAndView mav = new ModelAndView("system/role/updateRole");
		
		param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());
		param.setResourceStatus(AvailStatusEnum.AVAILABLE.getKey());
		List<ResourceVo> voList = resourceBusiness.queryPackageAllRoleResources(param);
		
		mav.addObject("nodes", JSONArray.toJSONString(voList));
        mav.addObject("roleId", param.getRoleId());
		return mav;
	}
    
    /**
     * 获取角色列表
     * 包含不可用角色
     */
    @RequestMapping("/getRoleList")
    public void getRoleList(HttpServletResponse response, AuthParam param) {
    	logger.info("[角色管理]查询角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			PageInfo<RoleModel> pageInfo = roleBusiness.queryPageRoleByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<RoleModel>> result = PaginationResult.newInstance(pageInfo.getList());
			logger.info("【角色管理】获取角色列表长度为:" + total);
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[角色管理]查询角色_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[角色管理]查询角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
    }
    
    /**
     * 增加角色
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public Object addRole(RoleModel role) {
    	logger.info("[角色管理]增加角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(role));
    	ALMResponse res = null;
        try {
            if (StringUtils.isBlank(role.getRoleName()) ) {
                return new ALMResponse(RetCode.INVALID_ARGS);
            }
            role.setStatus(AvailStatusEnum.AVAILABLE.getKey());// 可用状态
            int result = roleBusiness.addResource(role);
            logger.info("【角色管理】增加角色,成功条数:"+result);
            res = new ALMResponse(RetCode.SUCCESS);
        } catch (Exception e) {
        	logger.error("[角色管理]增加角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE);
        }
        logger.info("[角色管理]增加角色_结束,操作人:"+LogUtil.getCurrentUserName());
        return res;
    }
    
    /**
     * 通过角色id,获取角色
     */
    @RequestMapping("/queryRoleById")
    @ResponseBody
    public Object queryRoleById(AuthParam param) {
    	logger.info("[角色管理]通过角色id获取角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
    	ALMResponse res = null;
    	try {
    		if (param.getRoleId() == null ) {
    			return new ALMResponse(RetCode.INVALID_ARGS);
    		}
    		// 根据角色id,获取角色
    		List<RoleModel> list = roleBusiness.queryAllRoleByParam(param);
			res = new ALMResponse(RetCode.SUCCESS);
			res.setData(list.get(0));
    	} catch (Exception e) {
    		logger.error("[角色管理]通过角色id获取角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
    		res = new ALMResponse(RetCode.FAILURE);
    	}
    	logger.info("[角色管理]通过角色id获取角色_结束,操作人:"+LogUtil.getCurrentUserName());
    	return res;
    }
    
    /**
     * 修改角色,保存
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public Object updateRole(Role role) {
    	logger.info("[角色管理]修改角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(role));
    	ALMResponse res = null;
        try {
            if (role.getRoleId() == null || StringUtils.isBlank(role.getRoleName()) || role.getStatus() == null) {
                return new ALMResponse(RetCode.INVALID_ARGS);
            }
            int result = roleBusiness.updateRoleById(role);
            res = new ALMResponse(RetCode.SUCCESS);
            logger.info("【角色管理】修改角色条数:"+result);
        } catch (Exception e) {
            logger.error("[角色管理]修改角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE);
        }
        logger.info("[角色管理]修改角色_结束,操作人:"+LogUtil.getCurrentUserName());
        return res;
    }
    
    /**
     * 修改保存角色资源
     */
    @RequestMapping("/updateRoleResource")
    @ResponseBody
    public Object updateRoleResource (RoleModel roleModel) {
    	logger.info("[角色管理]修改角色资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(roleModel));
    	ALMResponse res = null;
    	List<Long> resourceIdList = new ArrayList<>();
    	try {
    		if (StringUtils.isEmpty(roleModel.getResourceIdStr())) {
    			return new ALMResponse(RetCode.INVALID_ARGS);
    		}
    		String[] resourceIdArr = roleModel.getResourceIdStr().split(",");
    		
    		for (int i=0; i<resourceIdArr.length; i++) {
    			resourceIdList.add(Long.valueOf(resourceIdArr[i]));
    		}
    		roleModel.setResourceIdList(resourceIdList);
    		roleBusiness.updateRoleResource(roleModel);
    		res = new ALMResponse(RetCode.SUCCESS);
    		logger.info("【角色管理】修改角色资源成功");
    	} catch (Exception e) {
    		logger.error("[角色管理]修改角色资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
    		res = new ALMResponse(RetCode.FAILURE);
    	}
    	logger.info("[角色管理]修改角色资源_结束,操作人:"+LogUtil.getCurrentUserName());
    	return res;
    }
    
}
