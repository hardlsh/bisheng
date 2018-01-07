package com.bisheng.services.system.service;

import java.util.Map;

import com.bisheng.services.system.model.generated.RoleResourceExample;

public interface RoleResourceService {
	/**
	 * 删除数据
	 * @author shihao.li
	 */
	int deleteByExample (RoleResourceExample roleResourceExample);
	/**
	 * 批量插入数据
	 * @author shihao.li
	 */
	int batchInsert (Map<String, Object> addRoleResourceMap);

}
