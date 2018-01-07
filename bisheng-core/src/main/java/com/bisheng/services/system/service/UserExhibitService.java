package com.bisheng.services.system.service;

import java.util.Map;

import com.bisheng.services.system.model.generated.UserExhibitExample;

/**
 * 用户展馆服务
 * @author hp
 *
 */
public interface UserExhibitService {
	/**
	 * 删除用户展馆
	 */
	int deleteByExample(UserExhibitExample example);
	/**
	 * 批量插入用户展馆
	 */
	int batchInsert(Map<String, Object> addUserExhibitMap);
}
