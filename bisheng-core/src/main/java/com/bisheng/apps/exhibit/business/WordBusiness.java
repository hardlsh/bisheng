package com.bisheng.apps.exhibit.business;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.github.pagehelper.PageInfo;

/**
 * 文字存量业务类
 * 
 * @author lihao
 */
public interface WordBusiness {

	/**
	 * 根据查询参数查询文字入库信息
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordInByParam(ExhibitQueryParam param);

	/**
	 * 新建入库 批量插入展位文字信息、文字存量信息、文字入库信息
	 * @param param
	 */
	void newWordIn(ExhibitQueryParam param);

	/**
	 * 修改入库 修改文字存量信息、插入文字入库信息
	 * @param param
	 */
	void updateWordIn(ExhibitQueryParam param);

}
