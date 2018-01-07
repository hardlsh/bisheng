package com.bisheng.services.system.dao.customized;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.dao.generated.UserMapper;
import com.bisheng.services.system.model.customized.UserModel;
import com.bisheng.services.system.model.generated.User;

public interface UserDao extends UserMapper {

	List<UserModel> queryAllUserModelByParam(AuthParam param);
	
	List<UserModel> queryAuthRoles(AuthParam param);
	
	List<UserModel> selectPageByParam(AuthParam param);
	
	List<User> checkUserByParam(AuthParam param);
	
	void insertReturnId(User user);

	List<UserModel> queryAllAuthExhibits(AuthParam param);

}
