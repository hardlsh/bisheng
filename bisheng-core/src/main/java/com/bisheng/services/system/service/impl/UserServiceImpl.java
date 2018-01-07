package com.bisheng.services.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.UserDao;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;
import com.bisheng.services.system.model.generated.UserExample;
import com.bisheng.services.system.service.UserService;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	private UserDao getUserDao() {
		return sqlSession.getMapper(UserDao.class);
	}

	@Override
	public List<User> selectByExample(UserExample userExample) {
		return getUserDao().selectByExample(userExample);
	}

	@Override
	public PageInfo<UserModel> selectPageByParam(AuthParam param) {
		setStartPage(param);
		List<UserModel> list = getUserDao().selectPageByParam(param);
		PageInfo<UserModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<UserModel> queryAllUserModelByParam(AuthParam param) {
		return getUserDao().queryAllUserModelByParam(param);
	}

	@Override
	public PageInfo<UserModel> queryPageAuthRoles(AuthParam param) {
		setStartPage(param);
		List<UserModel> list = getUserDao().queryAuthRoles(param);
		PageInfo<UserModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<UserModel> queryAllAuthExhibits(AuthParam param) {
		return getUserDao().queryAllAuthExhibits(param);
	}

	@Override
	public PageInfo<UserModel> queryPageAuthExhibits(AuthParam param) {
		setStartPage(param);
		List<UserModel> list = getUserDao().queryAllAuthExhibits(param);
		PageInfo<UserModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	public int addUser(User user) {
		return getUserDao().insertSelective(user);
	}
	
	@Override
	public void addUserReturnId(User user) {
		getUserDao().insertReturnId(user);
	}

	@Override
	public int updateUserById(User user) {
		return getUserDao().updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> checkUserByParam(AuthParam param) {
		return getUserDao().checkUserByParam(param);
	}

}
