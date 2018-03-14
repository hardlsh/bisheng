package com.bisheng.services.exhibit.dao.customized;

import java.util.List;
import java.util.Map;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.dao.generated.BoothWordMapper;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.generated.BoothWord;

public interface BoothWordDao extends BoothWordMapper{

	/**
	 * 单表查询
	 * @param boothWord
	 * @return
	 */
	List<BoothWord> queryBoothWord(BoothWord boothWord);

	/**
	 * 根据参数,查询展位文字
	 */
	List<BoothWordModel> queryBoothWordByParam(ExhibitQueryParam param);

	/**
	 * 批量插入
	 */
	void batchInsert(Map<String, Object> map);
	
	/**
	 * 根据boothId,进行删除操作
	 * @param boothId
	 */
	int deleteByBoothId(Long boothId);

}
