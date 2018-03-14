package com.bisheng.services.system.service;

import java.util.Map;

/**
 * 用户展馆服务
 * @author hp
 *
 */
public interface UserExhibitService {
	/**
	 * 删除用户展馆
	 * @param userId
	 */
	int deleteByUserId(Long userId);

	/**
	 * 删除用户展馆
	 * @param exhibitId
	 * @return
	 */
	int deleteByExhibitId(Long exhibitId);

	/**
	 * 批量插入用户展馆
	 */
	int batchInsert(Map<String, Object> addUserExhibitMap);
}
