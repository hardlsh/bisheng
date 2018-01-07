package com.bisheng.services.exhibit.service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.generated.BoothWord;
import com.github.pagehelper.PageInfo;

/**
 * 展位文字服务类
 * 
 * @author lihao
 */
public interface BoothWordService {
	
	/**
	 * 批量插入展位文字
	 */
	void batchInsert(ExhibitQueryParam queryParam);

	/**
	 * 根据boothId,进行删除操作
	 */
	int deleteByBoothId(BoothWord boothWord);

	/**
	 * 分页查询展位文字
	 */
	PageInfo<BoothWordModel> queryPagedBoothWordByParam(ExhibitQueryParam param);
	
}
