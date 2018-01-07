package com.bisheng.services.system.service;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.ResourceModel;
import com.bisheng.services.system.model.generated.Resource;
import com.bisheng.services.system.model.generated.ResourceExample;

public interface ResourceService {
	/**
	 * 用户关联查询资源
	 */
	List<ResourceModel> queryUserResourceByParam(AuthParam param);
	/**
	 * 单表查询所有资源
	 */
	List<Resource> selectByExample(ResourceExample example);
	/**
	 * 角色关联查询资源
	 */
	List<ResourceModel> queryRoleResourceByParam(AuthParam param);
	/**
	 * 新增资源
	 */
	int addResource(Resource resource);
	/**
	 * 根据主键id, 修改资源
	 */
	int updateResourceById(Resource resource);
}
