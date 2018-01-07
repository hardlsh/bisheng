package com.bisheng.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.apps.system.business.RoleBusiness;
import com.bisheng.apps.system.business.UserBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.constant.Constants;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.enums.ExhibitStatusEnum;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.enums.UserStatusEnum;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private RoleBusiness roleBusiness;
	@Autowired
    private ExhibitBusiness exhibitBusiness;
	
	private Gson gson = new Gson();

	// 用户页面
	@RequestMapping("/toUser")
	public ModelAndView toUser() {
		ModelAndView mav = new ModelAndView("system/user/user");
		mav.addObject("userStatusEnum", EnumJsonConverter.buildEnumJson(UserStatusEnum.class));
		
		return mav;
	}

	// 增加用户页面
	@RequestMapping("/addUser")
	public ModelAndView goAddUser() {
		ModelAndView mav = new ModelAndView("system/user/addUser");
		//获取所有可用的角色
		AuthParam param = new AuthParam();
		param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());
		List<RoleModel> roleList = roleBusiness.queryAllRoleByParam(param);
		mav.addObject("roles", roleList);

		return mav;
	}

	/**
	 * 跳转至修改用户页面
	 */
	@RequestMapping("/toUpdateUser")
	public ModelAndView toUpdateUser(String userId) {
		ModelAndView mav = new ModelAndView("system/user/updateUser");

		AuthParam param = new AuthParam();
		param.setUserId(Long.valueOf(userId));
		List<UserModel> userList = userBusiness.queryAllUserModelByParam(param);
		
		mav.addObject("user", userList.get(0));
		return mav;
	}

	/**
	 * 查询用户
	 */
	@RequestMapping("/getUserList")
	public void getUserList(HttpServletResponse response, AuthParam param) {
		logger.info("[用户管理]查询用户_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			// 分页查询
			PageInfo<UserModel> pageInfo = userBusiness.queryPageUserByParam(param);
			int total = (int) pageInfo.getTotal();
			logger.info("【用户管理】分页获取所有的用户列表,条数:"+total);
			PaginationResult<List<UserModel>> result = PaginationResult.newInstance(pageInfo.getList());

			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[用户管理]查询用户_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[用户管理]查询用户_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
	}
	
	/**
	 * 根据用户id,关联查询用户拥有权限的展馆
	 * 用户 激活状态
	 * 展馆 开业状态
	 */
	@RequestMapping("/queryExhibitList")
	public void queryExhibitList(HttpServletResponse response, AuthParam param) {
		logger.info("[用户管理]查询用户有权限的展馆_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			if (param.getUserId() == null) {// 初始化
				PaginationResult<List<UserModel>> res = PaginationResult.newInstance(new ArrayList<UserModel>());
				res.setiTotalRecords(0);
				res.setiTotalDisplayRecords(0);
				actionResult4Json(res.json(), response);
				return;
			}
			param.setUserStatus(UserStatusEnum.ACTIVE.getKey());// 用户  激活状态
			param.setRoleStatus(ExhibitStatusEnum.START.getKey());// 展馆 开业状态
			
			PageInfo<UserModel> pageInfo = userBusiness.queryPageAuthExhibits(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<UserModel>> result = PaginationResult.newInstance(pageInfo.getList());
			
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[用户管理]查询用户有权限的展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[用户管理]查询用户有权限的展馆_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
	}
	
	/**
	 * 根据用户id,关联查询用户拥有权限的角色
	 * 用户 激活状态
	 * 角色 可用状态
	 */
	@RequestMapping("/queryRoleList")
	public void queryRoleList(HttpServletResponse response, AuthParam param) {
		logger.info("[用户管理]查询用户有权限的角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			if (param.getUserId() == null) {// 初始化
				PaginationResult<List<UserModel>> res = PaginationResult.newInstance(new ArrayList<UserModel>());
				res.setiTotalRecords(0);
				res.setiTotalDisplayRecords(0);
				actionResult4Json(res.json(), response);
				return;
			}
			param.setUserStatus(UserStatusEnum.ACTIVE.getKey());// 用户  激活状态
			param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());// 角色 可用状态
			
			PageInfo<UserModel> pageInfo = userBusiness.queryPageAuthRoles(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<UserModel>> result = PaginationResult.newInstance(pageInfo.getList());
			logger.info("【用户管理】根据用户id,分页查询资金方产品,条数:"+total);
			
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[用户管理]查询用户有权限的角色_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[用户管理]查询用户有权限的角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
	}

	/**
	 * 新增用户 保存
	 */
	@RequestMapping("/saveUser")
	@ResponseBody
	public Object saveUser(AuthParam param) {
		logger.info("[用户管理]新增用户_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			if (StringUtils.isBlank(param.getPhone()) || StringUtils.isBlank(param.getUserName())
					|| param.getRoleIdList() == null || StringUtils.isBlank(param.getLoginName())) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			String msg = validate(param);
			if (StringUtils.isNotBlank(msg)) {
				return new ALMResponse(RetCode.INVALID_ARGS, msg);
			}
			param.setUserStatus(UserStatusEnum.CREATE.getKey());

			// 加密密码
			Md5Hash md5Hash = new Md5Hash(Constants.DEFAULT_PWD, Constants.SALT, Constants.HASH_ITERATIONS);
			param.setPassword(md5Hash.toString());

			List<User> checkList = userBusiness.checkUserByParam(param);
			if (checkList != null && checkList.size() > 0) {
				return new ALMResponse(RetCode.FAILURE, "用户名已存在，请检查！");
			}
			userBusiness.addUser(param);
			logger.info("【用户管理】新增用户,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[用户管理]新增用户_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[用户管理]新增用户_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	

	/**
	 * 修改用户密码和状态
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public Object resetPwd(User user) {
		logger.info("[用户管理]修改用户密码和状态_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(user));
		ALMResponse res = null;
		try {
			if (StringUtils.isNotBlank(user.getPassword())) {
				// 加密密码
				Md5Hash md5Hash = new Md5Hash(user.getPassword(), Constants.SALT, Constants.HASH_ITERATIONS);
				user.setPassword(md5Hash.toString());
			}
			if (user.getPassword().equals("")) {
				user.setPassword(null);//如果页面没有输入,则密码置为null, 不修改密码
			}

			int result = userBusiness.updateUserById(user);
			logger.info("【用户管理】修改用户密码和状态,成功条数:" + result);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[用户管理]修改用户密码和状态_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE, "修改用户密码和状态失败");
		}
		logger.info("[用户管理]修改用户密码和状态_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
	/**
	 * 修改用户密码
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Object updatePwd(User user) {
		logger.info("[用户管理]修改用户密码_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(user));
		ALMResponse res = null;
		try {
			if (StringUtils.isBlank(user.getPassword())) {
				res = new ALMResponse(RetCode.FAILURE, "用户密码不能为空");
			}
			// 获取当前用户
			Subject subject = SecurityUtils.getSubject();
			User currentUser = (User)subject.getPrincipal();
			user.setUserId(currentUser.getUserId());
			// 加密密码
			Md5Hash md5Hash = new Md5Hash(user.getPassword(), Constants.SALT, Constants.HASH_ITERATIONS);
			user.setPassword(md5Hash.toString());
			int result = userBusiness.updateUserById(user);
			logger.info("【用户管理】修改用户密码,成功条数:" + result);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[用户管理]修改用户密码_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE, "修改用户密码失败");
		}
		logger.info("[用户管理]修改用户密码_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
	/**
	 * 查询所有的角色         修改页面
	 */
	@RequestMapping("/queryAllRoleList")
	public void queryAllRoleList(HttpServletResponse response, AuthParam param) {
		logger.info("[用户管理]查询所有的角色_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			PageInfo<RoleModel> pageInfo = roleBusiness.queryPageRoleByParam(param);
			
			int total = (int) pageInfo.getTotal();
			logger.info("【用户管理】分页查询所有角色,条数:"+total);
			PaginationResult<List<RoleModel>> result = PaginationResult.newInstance(pageInfo.getList());
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[用户管理]查询所有的角色_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[用户管理]查询所有的角色_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
	}
	
	/**
	 * 查询所有的展馆		修改页面
	 */
	@RequestMapping("/queryAllExhibitList")
	public void queryAllExhibitList(HttpServletResponse response, AuthParam param){
		logger.info("[用户管理]查询所有的展馆_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		try {
			ExhibitQueryParam exhibitParam = new ExhibitQueryParam();
			List<ExhibitModel> exhibitList = exhibitBusiness.queryAllExhibitByParam(exhibitParam);
			
			//根据当前用户查询拥有数据权限的展馆
			param.setUserStatus(UserStatusEnum.ACTIVE.getKey());// 用户  激活状态
			param.setExhibitStatus(ExhibitStatusEnum.START.getKey());// 展馆 开业状态
			List<UserModel> userList = userBusiness.queryAllAuthExhibits(param);
			
			// 组装页面数据
			List<UserModel> list = new ArrayList<>();
			UserModel userModel = null;
			for (ExhibitModel exhibit : exhibitList) {
				userModel = new UserModel();
				MBeanUtil.copyProperties(exhibit, userModel);
				for (UserModel user : userList) {
					if (exhibit.getExhibitId().equals(user.getExhibitId())) {
						userModel.setExhibitSel(true);
						break;
					}
				}
				list.add(userModel);
			}
			int total = list.size();
			PaginationResult<List<UserModel>> result = PaginationResult.newInstance(list);
			
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("[用户管理]查询所有的展馆_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("[用户管理]查询所有的展馆_异常,操作人:" + LogUtil.getCurrentUserName() + ",异常原因:", e);
		}
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Object updateUser (AuthParam param) {
		logger.info("[用户管理]修改用户资料_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			if (StringUtils.isBlank(param.getLoginName()) || StringUtils.isBlank(param.getUserName()) 
					|| param.getExhibitIdArr().length == 0) {
				return new ALMResponse(RetCode.INVALID_ARGS);
			}
			String msg = validate(param);
			if (StringUtils.isNotBlank(msg)) {
				return new ALMResponse(RetCode.INVALID_ARGS, msg);
			}
			List<User> checkList = userBusiness.checkUserByParam(param);
			if (checkList != null && checkList.size() >=1) {
				if(checkList.size() >1) {
					return new ALMResponse(RetCode.FAILURE, "用户登录名已存在，请检查！");
				}else {
					User model = checkList.get(0);
					if(!model.getUserId().equals(param.getUserId())) {
						return new ALMResponse(RetCode.FAILURE, "用户登录名已存在，请检查！");
					}
				}
			}
			
			userBusiness.updateUserModel(param);
			logger.info("【用户管理】修改用户,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("[用户管理]修改用户资料_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("[用户管理]修改用户资料_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}

	public static final String PHONE = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[0-9])\\d{8}$";

	public static final String EMAIL = "^([a-zA-Z0-9_\\.-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";

	/**
	 * 校验手机号码和邮箱
	 */
	private String validate(AuthParam param) {
		logger.info("【用户管理】校验手机号码和邮箱,入参:"+JSONObject.toJSONString(param));
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(param.getEmail()) && !param.getEmail().matches(EMAIL)) {
			sb.append("邮箱格式不正确<br>");
		}
		if (StringUtils.isNotBlank(param.getPhone()) && !param.getPhone().matches(PHONE)) {
			sb.append("手机格式不正确<br>");
		}
		return sb.toString();
	}
	
}
