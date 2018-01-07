package com.bisheng.services.system.service;

import java.util.List;
import java.util.Map;

import com.bisheng.services.system.model.generated.UserRole;
import com.bisheng.services.system.model.generated.UserRoleExample;

/** 
 * @ClassName: UserRoleService 
 * @author: shihao.li
 * @Explain: 用户角色表
 * @date: 2017年3月28日 下午10:43:25  
 */
public interface UserRoleService {
	/**
	 * 查询用户角色
	 */
	List<UserRole> selectByExample(UserRoleExample userRoleExample);
	/**
	 * 新增用户角色
	 */
	int addUserRole(UserRole userRole);
	/**
	 * 删除用户角色
	 */
	int deleteByExample(UserRoleExample userRoleExample);
	/**
	 * 批量插入用户角色
	 */
	int batchInsert(Map<String, Object> addUserRoleMap);
	
}
