package com.bisheng.services.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.RoleDao;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.generated.Role;
import com.bisheng.services.system.service.RoleService;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
    private RoleDao getRoleDao() {
    	return sqlSession.getMapper(RoleDao.class);
    }

    @Override
    public List<RoleModel> queryAllRoleByParam(AuthParam param) {
    	return getRoleDao().queryRoleByParam(param);
    }
    
	@Override
	public PageInfo<RoleModel> queryPageRoleByParam(AuthParam param) {
		setStartPage(param);
		List<RoleModel> list = getRoleDao().queryRoleByParam(param);
		PageInfo<RoleModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int addResource(Role role) {
		return getRoleDao().insertSelective(role);
	}

	@Override
	public int updateRoleById(Role role) {
		return getRoleDao().updateByPrimaryKeySelective(role);
	}

}
