package com.bisheng.controller.system;

import java.util.List;

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
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.enums.ResourceLevelEnum;
import com.bisheng.services.system.enums.ResourceTypeEnum;
import com.bisheng.services.system.model.generated.Resource;
import com.bisheng.services.system.vo.ResourceVo;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.google.gson.Gson;

@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController{
	// 创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceBusiness resourceBusiness;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/toResource")
	public ModelAndView toResource(){
		ModelAndView mav = new ModelAndView("system/resource/resource");
		
		Resource resource = new Resource();
		List<ResourceVo> voList = resourceBusiness.queryPackageAllResources(resource);
		
		//字典项
		mav.addObject("resourceTypeEnum", EnumJsonConverter.buildEnumJson(ResourceTypeEnum.class));
		mav.addObject("availStatusEnum", EnumJsonConverter.buildEnumJson(AvailStatusEnum.class));
		mav.addObject("nodes", JSONArray.toJSONString(voList));
		return mav;
	}
	
	@RequestMapping("/toAddResource")
	public ModelAndView toAddResource() {
		ModelAndView mav = new ModelAndView("system/resource/addResource");
		return mav;
	}
	
	@RequestMapping("/toUpdateResource")
	public ModelAndView toUpdateResource(Resource resource) {
		ModelAndView mav = new ModelAndView("system/resource/updateResource");
		//根据资源id,获取对应的资源
		List<Resource> resourceList = resourceBusiness.queryResourceByExample(resource);
		mav.addObject("resource", resourceList.get(0));
		return mav;
	}
	
	/**
	 * 根据资源类型,获取对应的可用资源
	 */
	@RequestMapping("/getResourceByType")
	@ResponseBody
	public Object getResourceByType(Resource resource) {
		logger.info("[资源管理]根据资源类型获取资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		ALMResponse res = null;
		try {
			if (resource.getResourceType() == null) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			if (resource.getResourceType().intValue() == ResourceTypeEnum.MENU.getKey()) {
				resource.setLevel(1);// 一级菜单
			} else if (resource.getResourceType().intValue() == ResourceTypeEnum.BUTTON.getKey()) {
				resource.setLevel(2);// 二级菜单
				resource.setResourceType(ResourceTypeEnum.MENU.getKey());// 菜单
			}
			resource.setStatus(AvailStatusEnum.AVAILABLE.getKey());
			List<Resource> resourceList = resourceBusiness.queryResourceByExample(resource);
			logger.info("【资源管理】根据资源类型,获取资源,资源长度为:"+resourceList.size());
			res = new ALMResponse(RetCode.SUCCESS);
			res.setData(resourceList);
		} catch (Exception e) {
			logger.error("[资源管理]根据资源类型获取资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[资源管理]根据资源类型获取资源_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	/**
	 * 根据资源id,获取对应的资源
	 */
	@RequestMapping("/getResourceById")
	@ResponseBody
	public Object getResourceById(Resource resource) {
		logger.info("[资源管理]根据资源id获取资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		ALMResponse res = null;
		try {
			if (resource.getResourceId() == null) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			//根据资源id,获取对应的资源
			List<Resource> resourceList = resourceBusiness.queryResourceByExample(resource);
			logger.info("【资源管理】根据资源id,获取资源,资源长度为:"+resourceList.size());
			res = new ALMResponse(RetCode.SUCCESS);
			res.setData(resourceList.get(0));
		} catch (Exception e) {
			logger.error("[资源管理]根据资源id获取资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[资源管理]根据资源id获取资源_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	/**
	 * 新增资源,保存
	 */
	@RequestMapping("/addResource")
	@ResponseBody
	public Object addResource(Resource resource) {
		logger.info("[资源管理]新增资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		ALMResponse res = null;
		try {
			if (StringUtils.isBlank(resource.getResourceName()) || resource.getResourceType() == null) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			int resourceType = resource.getResourceType().intValue();
			if (resourceType == ResourceTypeEnum.MENU.getKey() && resource.getParentId() == null) {
				resource.setLevel(ResourceLevelEnum.FIRSTMENU.getKey());// 一级菜单
			}else if (resourceType == ResourceTypeEnum.MENU.getKey() && resource.getParentId() != null) {
				resource.setLevel(ResourceLevelEnum.SECONDMENU.getKey());// 二级菜单
			}else if (resourceType == ResourceTypeEnum.BUTTON.getKey() && resource.getParentId() != null) {
				resource.setLevel(ResourceLevelEnum.BUTTON.getKey());// 按钮
			}
			// 不是一级菜单
			if (resource.getLevel().intValue() != ResourceLevelEnum.FIRSTMENU.getKey()) {
				if (StringUtils.isBlank(resource.getResourcePath()) || resource.getParentId() == null) {
					res = new ALMResponse(RetCode.FAILURE);
					res.setResultMsg("不是一级菜单, 上级资源和资源地址不能为空");
					return res;
				}
			}
			int result = resourceBusiness.addResource(resource);
			logger.info("【资源管理】新增资源保存,保存条数:" + result);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[资源管理]新增资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[资源管理]新增资源_结束,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		return res;
	}
	/**
	 * 根据资源id,修改状态为不可用
	 */
	@RequestMapping("/delResource")
	@ResponseBody
	public Object delResource(Resource resource) {
		logger.info("[资源管理]删除资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		ALMResponse res = null;
		try {
			resource.setStatus(AvailStatusEnum.UNAVAILABLE.getKey());
			int result = resourceBusiness.updateResourceById(resource);
			logger.info("【资源管理】删除资源,删除条数:"+result);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[资源管理]删除资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[资源管理]删除资源_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	/**
	 * 修改资源
	 */
	@RequestMapping("/updateResource")
	@ResponseBody
	public Object updateResource(Resource resource) {
		logger.info("[资源管理]修改资源_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(resource));
		ALMResponse res = null;
		try {
			if (StringUtils.isEmpty(resource.getResourceName())	|| resource.getResourceType() == null) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			int result = resourceBusiness.updateResourceById(resource);
			logger.info("【资源管理】修改资源,修改条数:"+result);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[资源管理]修改资源_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[资源管理]修改资源_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
}
