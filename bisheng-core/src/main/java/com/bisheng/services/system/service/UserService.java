package com.bisheng.services.system.service;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;
import com.bisheng.services.system.model.generated.UserExample;
import com.github.pagehelper.PageInfo;

/**
 * @author shihao.li
 * 用户相关查询及操作
 */
public interface UserService {
	/**
	 * 单表查询
	 */
	List<User> selectByExample(UserExample userExample);
	/**
	 * 单表分页查询用户
	 */
	PageInfo<UserModel> selectPageByParam(AuthParam param);
	/**
	 * 根据参数,查询全部用户
	 */
	List<UserModel> queryAllUserModelByParam(AuthParam param);
	/**
	 * 根据参数,分页查询用户拥有权限的角色
	 */
	PageInfo<UserModel> queryPageAuthRoles(AuthParam param);
	/**
	 * 查询用户拥有权限的全部展馆
	 */
	List<UserModel> queryAllAuthExhibits(AuthParam param);
	/**
	 * 分页查询用户拥有权限的全部展馆
	 */
	PageInfo<UserModel> queryPageAuthExhibits(AuthParam param);
	/**
	 * 新增用户
	 */
	int addUser(User user);
	/**
	 * 新增用户,并返回主键
	 */
	void addUserReturnId(User user);
	/**
	 * 根据主键id, 修改用户
	 */
	int updateUserById(User user);
	/**
	 * 校验用户信息
	 */
	List<User> checkUserByParam(AuthParam param);

}
