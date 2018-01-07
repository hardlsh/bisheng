package com.bisheng.services.system.dao.customized;

import java.util.Map;

import com.bisheng.services.system.dao.generated.RoleResourceMapper;

public interface RoleResourceDao extends RoleResourceMapper{

	int batchInsert(Map<String, Object> addRoleResourceMap);

}
