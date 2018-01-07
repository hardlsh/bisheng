package com.bisheng.apps.system.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bisheng.apps.system.business.UserBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;
import com.bisheng.services.system.model.generated.UserExample;
import com.bisheng.services.system.model.generated.UserExhibit;
import com.bisheng.services.system.model.generated.UserExhibitExample;
import com.bisheng.services.system.model.generated.UserRole;
import com.bisheng.services.system.model.generated.UserRoleExample;
import com.bisheng.services.system.service.UserExhibitService;
import com.bisheng.services.system.service.UserRoleService;
import com.bisheng.services.system.service.UserService;
import com.github.pagehelper.PageInfo;

@Service
public class UserBusinessImpl implements UserBusiness {
	//创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserExhibitService userExhibitService;
	
	@Override
	public User querySingleUserByExample(AuthParam param) {
		UserExample userExample = trantParam(param);
		List<User> list = userService.selectByExample(userExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<User> checkUserByParam(AuthParam param) {
		List<User> list = userService.checkUserByParam(param);
		return list;
	}

	@Override
	public PageInfo<UserModel> queryPageUserByParam(AuthParam param) {
		return userService.selectPageByParam(param);
	}
	
	@Override
	public List<UserModel> queryAllUserModelByParam(AuthParam param) {
		return userService.queryAllUserModelByParam(param);
	}

	@Override
	public PageInfo<UserModel> queryPageAuthRoles(AuthParam param) {
		return userService.queryPageAuthRoles(param);
	}

	@Override
	public List<UserModel> queryAllAuthExhibits(AuthParam param) {
		return userService.queryAllAuthExhibits(param);
	}

	@Override
	public PageInfo<UserModel> queryPageAuthExhibits(AuthParam param) {
		return userService.queryPageAuthExhibits(param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addUser(AuthParam param) {
		User user = new User();
		MBeanUtil.copyProperties(param, user);
		if (param.getUserStatus() != null) {
			user.setStatus(param.getUserStatus());
		}
		userService.addUserReturnId(user);// 插入后,自动为参数userId赋值
		
		List<UserRole> userRoleList = new ArrayList<>();
		UserRole userRole;
		int size = param.getRoleIdList().size();
		for (int i = 0; i < size; i++) {
			userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(param.getRoleIdList().get(i));
			userRoleList.add(userRole);
		}
		
		Map<String, Object> addUserRoleMap = new HashMap<>();
		addUserRoleMap.put("userRoleList", userRoleList);
		int addUserRoleRes = userRoleService.batchInsert(addUserRoleMap);
		logger.info("【用户管理】新增用户id:" + user.getUserId() + 
				",新增用户角色:" + addUserRoleRes + "条");
	}

	@Override
	public int updateUserById(User user) {
		return userService.updateUserById(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateUserModel(AuthParam param) {
		// 修改用户表
		User user = new User();
		MBeanUtil.copyProperties(param, user);
		int updateUserRes = userService.updateUserById(user);
		
		// 修改用户角色表
		int updateUserRole = updateUserRoleByParam(param);
		// 修改用户展馆表
		int updateUserExhibit = updateUserExhibitByParam(param);
		logger.info("【用户管理】修改用户:" + updateUserRes + 
				"条,修改用户角色:" + updateUserRole + "条,修改用户展馆:"
				+ updateUserExhibit + "条,对应用户id:" + param.getUserId());
		
		return updateUserRes;
	}
	/**
	 * 修改用户角色分两步, 先删除, 再新增
	 */
	private int updateUserRoleByParam(AuthParam param) {
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(param.getUserId());
		int delRes = userRoleService.deleteByExample(example);

		List<UserRole> userRoleList = new ArrayList<>();
		for (int i = 0; i < param.getRoleIdList().size(); i++) {
			UserRole userRole = new UserRole();
			userRole.setUserId(param.getUserId());
			userRole.setRoleId(param.getRoleIdList().get(i));
			userRoleList.add(userRole);
		}
		Map<String, Object> addUserRoleMap = new HashMap<>();
		addUserRoleMap.put("userRoleList", userRoleList);
		int addRes = userRoleService.batchInsert(addUserRoleMap);
		logger.info("【用户管理】删除用户角色:" + delRes + 
				"条,新增用户角色:" + addRes + "条,对应用户id:" + param.getUserId());
		return addRes;
	}
	/**
	 * 修改用户展馆分两步, 先删除, 再新增
	 */
	private int updateUserExhibitByParam(AuthParam param) {
		UserExhibitExample example = new UserExhibitExample();
		UserExhibitExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(param.getUserId());
		int delRes = userExhibitService.deleteByExample(example);

		List<UserExhibit> userExhibitList = new ArrayList<>();
		UserExhibit userExhibit = null;
		for (int i = 0; i < param.getExhibitIdArr().length; i++) {
			userExhibit = new UserExhibit();
			userExhibit.setUserId(param.getUserId());
			userExhibit.setExhibitId(param.getExhibitIdArr()[i]);
			userExhibitList.add(userExhibit);
		}
		Map<String, Object> addUserExhibitMap = new HashMap<>();
		addUserExhibitMap.put("userExhibitList", userExhibitList);
		int addRes = userExhibitService.batchInsert(addUserExhibitMap);
		logger.info("【用户管理】删除用户展馆:" + delRes + 
				"条,新增用户展馆:" + addRes + "条,对应用户id:" + param.getUserId());
		return addRes;
	}
	
	/**
	 * 转换参数
	 */
	private UserExample trantParam(AuthParam param) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		if (param.getUserName() != null) {
			criteria.andUserNameEqualTo(param.getUserName());
		}
		if (param.getLoginName() != null) {
			criteria.andLoginNameEqualTo(param.getLoginName());
		}
		return userExample;
	}

}
