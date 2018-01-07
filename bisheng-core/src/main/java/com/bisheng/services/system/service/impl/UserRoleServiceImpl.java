package com.bisheng.services.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.UserRoleDao;
import com.bisheng.services.system.model.generated.UserRole;
import com.bisheng.services.system.model.generated.UserRoleExample;
import com.bisheng.services.system.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseService implements UserRoleService{
    private UserRoleDao getUserRoleDao() {
        return sqlSession.getMapper(UserRoleDao.class);
    }
	
	@Override
	public List<UserRole> selectByExample(UserRoleExample userRoleExample) {
		return getUserRoleDao().selectByExample(userRoleExample);
	}

	@Override
	public int addUserRole(UserRole userRole) {
		return getUserRoleDao().insertSelective(userRole);
	}

	@Override
	public int deleteByExample(UserRoleExample userRoleExample) {
		return getUserRoleDao().deleteByExample(userRoleExample);
	}

	@Override
	public int batchInsert(Map<String, Object> addUserRoleMap) {
		return getUserRoleDao().batchInsert(addUserRoleMap);
	}
	
}
