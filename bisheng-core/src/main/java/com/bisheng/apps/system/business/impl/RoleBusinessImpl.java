package com.bisheng.apps.system.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bisheng.apps.system.business.RoleBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.generated.Role;
import com.bisheng.services.system.model.generated.RoleResource;
import com.bisheng.services.system.model.generated.RoleResourceExample;
import com.bisheng.services.system.service.RoleResourceService;
import com.bisheng.services.system.service.RoleService;
import com.github.pagehelper.PageInfo;

@Service
public class RoleBusinessImpl implements RoleBusiness {
	//创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(RoleBusinessImpl.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Override
	public List<RoleModel> queryAllRoleByParam(AuthParam param) {
		return roleService.queryAllRoleByParam(param);
	}

	@Override
	public PageInfo<RoleModel> queryPageRoleByParam(AuthParam param) {
		return roleService.queryPageRoleByParam(param);
	}

	@Override
	public List<RoleModel> queryUserRoleByParam(AuthParam param) {
		return roleService.queryUserRoleByParam(param);
	}

	@Override
	public int addResource(Role role) {
		return roleService.addResource(role);
	}

	@Override
	public int updateRoleById(Role role) {
		return roleService.updateRoleById(role);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateRoleResource(RoleModel roleModel) {
		RoleResourceExample roleResourceExample = new RoleResourceExample();
		RoleResourceExample.Criteria criteria = roleResourceExample.createCriteria();
		criteria.andRoleIdEqualTo(roleModel.getRoleId());
		int delRes = roleResourceService.deleteByExample(roleResourceExample);
		
		List<RoleResource> roleResourceList = new ArrayList<>();
		if (roleModel.getResourceIdList().size() > 0) {
			for (int i=0; i<roleModel.getResourceIdList().size(); i++) {
				RoleResource roleResource = new RoleResource();
				roleResource.setRoleId(roleModel.getRoleId());
				roleResource.setResourceId(roleModel.getResourceIdList().get(i));
				roleResourceList.add(roleResource);
			}
		}
		Map<String, Object> addRoleResMap = new HashMap<>();
		addRoleResMap.put("roleResourceList", roleResourceList);
		int addRes = roleResourceService.batchInsert(addRoleResMap);
		logger.info("【角色管理】删除角色资源:" + delRes + 
				"条,新增角色资源:" + addRes + "条,对应角色id:" + roleModel.getRoleId());
		return addRes;
	}
	
}
