package com.bisheng.services.exhibit.service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.generated.BoothWord;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 展位文字服务类
 * 
 * @author lihao
 */
public interface BoothWordService {

	/**
	 * 单表查询
	 * @param boothWord
	 * @return
	 */
	List<BoothWord> queryBoothWord(BoothWord boothWord);

	/**
	 * 分页查询展位文字
	 */
	PageInfo<BoothWordModel> queryPagedBoothWordByParam(ExhibitQueryParam param);

	/**
	 * 批量插入展位文字
	 */
	void batchInsert(List<BoothWordModel> boothWordList);

	/**
	 * 根据boothId,进行删除操作
	 * @param boothId
	 */
	int deleteByBoothId(Long boothId);

}
