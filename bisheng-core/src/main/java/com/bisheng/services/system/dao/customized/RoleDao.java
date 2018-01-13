package com.bisheng.services.system.dao.customized;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.dao.generated.RoleMapper;
import com.bisheng.services.system.model.customized.RoleModel;

public interface RoleDao extends RoleMapper {
	List<RoleModel> queryRoleByParam(AuthParam param);

	/**
	 * 根据参数,查询用户关联角色
	 * @param param
	 * @return
	 */
	List<RoleModel> queryUserRoleByParam(AuthParam param);
}
