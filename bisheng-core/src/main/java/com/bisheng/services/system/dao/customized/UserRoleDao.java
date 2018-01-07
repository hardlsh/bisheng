package com.bisheng.services.system.dao.customized;

import java.util.Map;

import com.bisheng.services.system.dao.generated.UserRoleMapper;

public interface UserRoleDao extends UserRoleMapper {

	int batchInsert(Map<String, Object> addUserRoleMap);

}
