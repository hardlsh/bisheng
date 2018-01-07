package com.bisheng.services.exhibit.service;

import java.util.List;

import com.bisheng.services.exhibit.model.generated.WordOperate;

/**
 * 文字操作记录
 * 
 * @author lihao
 */
public interface WordOperateService {
	
	/**
	 * 批量插入文字操作记录
	 * @param wordOperateList
	 */
	void batchInsert(List<WordOperate> wordOperateList);
}
