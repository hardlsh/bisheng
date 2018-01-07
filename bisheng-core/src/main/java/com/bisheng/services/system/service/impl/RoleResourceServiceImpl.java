package com.bisheng.services.system.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.RoleResourceDao;
import com.bisheng.services.system.model.generated.RoleResourceExample;
import com.bisheng.services.system.service.RoleResourceService;

@Service
public class RoleResourceServiceImpl extends BaseService implements RoleResourceService {
	private RoleResourceDao getRoleResourceDao () {
		return sqlSession.getMapper(RoleResourceDao.class);
	}
	
	@Override
	public int deleteByExample(RoleResourceExample roleResourceExample) {
		return getRoleResourceDao().deleteByExample(roleResourceExample);
	}

	@Override
	public int batchInsert(Map<String, Object> addRoleResourceMap) {
		return getRoleResourceDao().batchInsert(addRoleResourceMap);
	}

}
