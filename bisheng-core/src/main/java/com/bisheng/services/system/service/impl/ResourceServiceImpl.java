package com.bisheng.services.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.ResourceDao;
import com.bisheng.services.system.model.customized.ResourceModel;
import com.bisheng.services.system.model.generated.Resource;
import com.bisheng.services.system.model.generated.ResourceExample;
import com.bisheng.services.system.service.ResourceService;

@Service
public class ResourceServiceImpl extends BaseService implements ResourceService {
	private ResourceDao getResourceDao(){
		return sqlSession.getMapper(ResourceDao.class);
	}
	
	@Override
	public List<ResourceModel> queryUserResourceByParam(AuthParam param) {
		return getResourceDao().queryUserResourceByParam(param);
	}
	
	@Override
	public List<Resource> selectByExample(ResourceExample example) {
		return getResourceDao().selectByExample(example);
	}
	
	@Override
	public List<ResourceModel> queryRoleResourceByParam(AuthParam param) {
		return getResourceDao().queryRoleResourceByParam(param);
	}
	
	@Override
	public int addResource(Resource resource) {
		return getResourceDao().insertSelective(resource);
	}

	@Override
	public int updateResourceById(Resource resource) {
		return getResourceDao().updateByPrimaryKeySelective(resource);
	}
}
