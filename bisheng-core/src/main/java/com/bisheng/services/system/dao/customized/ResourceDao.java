package com.bisheng.services.system.dao.customized;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.dao.generated.ResourceMapper;
import com.bisheng.services.system.model.customized.ResourceModel;

public interface ResourceDao extends ResourceMapper{
	List<ResourceModel> queryUserResourceByParam(AuthParam param);
	
	List<ResourceModel> queryRoleResourceByParam(AuthParam param);

}
