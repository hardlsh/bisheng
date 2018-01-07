package com.bisheng.apps.system.business;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.ResourceModel;
import com.bisheng.services.system.model.generated.Resource;
import com.bisheng.services.system.vo.ResourceVo;

/** 
 * @ClassName: ResourceBusiness 
 * @author: shihao.li
 * @Explain: 资源管理业务
 * @date: 2017年3月27日 下午3:23:02  
 */
public interface ResourceBusiness {
	/**
	 * 根据参数,查询用户关联资源
	 */
	List<ResourceModel> queryUserResourceByParam(AuthParam param);
	/**
	 * 根据参数,获取组装好的菜单用户资源
	 */
	List<ResourceVo> queryPackageMenuUserResources(AuthParam param);
	/**
	 * 根据参数,获取组装好的所有角色资源
	 */
	List<ResourceVo> queryPackageAllRoleResources(AuthParam param);
	/**
	 * 根据参数,获取组装好的所有资源
	 */
	List<ResourceVo> queryPackageAllResources(Resource resource);
	/**
	 * 单表查询资源
	 */
	List<Resource> queryResourceByExample(Resource resource);	
	/**
	 * 新增资源
	 */
	int addResource(Resource resource);
	/**
	 * 根据主键id, 修改资源
	 */
	int updateResourceById(Resource resource);
}
