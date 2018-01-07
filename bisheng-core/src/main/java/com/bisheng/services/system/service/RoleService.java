package com.bisheng.services.system.service;

import java.util.List;

import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.generated.Role;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: RoleService 
 * @author: shihao.li
 * @Explain: 角色管理相关操作
 * @date: 2017年3月24日 下午4:49:44  
 */
public interface RoleService {
	/**
	 * 根据参数,查询全部角色
	 */
	List<RoleModel> queryAllRoleByParam(AuthParam param);
	/**
	 * 根据参数,分页查询角色
	 */
	PageInfo<RoleModel> queryPageRoleByParam(AuthParam param);
	/**
	 * 新增角色
	 */
	int addResource(Role role);
	/**
	 * 根据主键id, 修改角色
	 */
	int updateRoleById(Role role);
	
}
