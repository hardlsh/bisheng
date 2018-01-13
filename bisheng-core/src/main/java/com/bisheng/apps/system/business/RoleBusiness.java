package com.bisheng.apps.system.business;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.generated.Role;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: RoleBusiness 
 * @author: shihao.li
 * @Explain: 角色管理及相关业务
 * @date: 2017年3月27日 下午3:22:58  
 */
public interface RoleBusiness {
	/**
	 * 根据参数,查询全部角色
	 */
	List<RoleModel> queryAllRoleByParam(AuthParam param);
	/**
	 * 根据参数,分页查询角色
	 */
	PageInfo<RoleModel> queryPageRoleByParam(AuthParam param);

	/**
	 * 根据参数,查询用户关联角色
	 * @param param
	 * @return
	 */
	List<RoleModel> queryUserRoleByParam(AuthParam param);

	/**
	 * 新增角色
	 */
	int addResource(Role role);
	/**
	 * 根据主键id, 修改角色
	 */
	int updateRoleById(Role role);
	/**
	 * 修改角色资源
	 */
	int updateRoleResource(RoleModel roleModel);
}
