package com.bisheng.services.exhibit.dao.customized;

import java.util.Map;

public interface WordOperateDao {
	
	/**
	 * 批量插入文字操作记录
	 * @param map
	 */
	void batchInsert(Map<String, Object> map);
}
