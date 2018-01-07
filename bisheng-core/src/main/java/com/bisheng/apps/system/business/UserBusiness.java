package com.bisheng.apps.system.business;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: UserBusiness 
 * @author: shihao.li
 * @Explain: 用户管理及数据权限相关业务
 * @date: 2017年3月27日 下午3:22:52  
 */
public interface UserBusiness {
	/**
	 * 单表查询单个用户
	 */
	User querySingleUserByExample(AuthParam param);
	/**
	 * 校验用户信息
	 */
	List<User> checkUserByParam(AuthParam param);
	/**
	 * 单表分页查询用户
	 */
	PageInfo<UserModel> queryPageUserByParam(AuthParam param);
	/**
	 * 根据参数,查询全部用户Model
	 */
	List<UserModel> queryAllUserModelByParam(AuthParam param);
	/**
	 * 根据参数,分页查询用户拥有权限的角色
	 */
	PageInfo<UserModel> queryPageAuthRoles(AuthParam param);
	/**
	 * 新增用户
	 */
	void addUser(AuthParam param);
	/**
	 * 根据主键id, 修改用户
	 */
	int updateUserById(User user);
	/**
	 * 修改用户,及用户关联的角色权限
	 */
	int updateUserModel(AuthParam param);
	/**
	 * 查询用户拥有权限的全部展馆
	 */
	List<UserModel> queryAllAuthExhibits(AuthParam param);
	/**
	 * 分页查询用户拥有权限的全部展馆
	 */
	PageInfo<UserModel> queryPageAuthExhibits(AuthParam param);

}
