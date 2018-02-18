package com.bisheng.apps.exhibit.business;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
	 * 根据查询参数查询文字存量信息
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordByParam(ExhibitQueryParam param);

	/**
	 * 根据查询参数查询文字存量信息,导出
	 * @param param
	 * @return
	 */
	List<WordModel> queryWordListByParam(ExhibitQueryParam param);

	/**
	 * 批量插入文字存量信息、文字入库信息
	 *
	 * @param param
	 */
	void batchInsertWordIn(ExhibitQueryParam param);

	/**
	 * 批量修改文字存量信息
	 * @param param
	 */
	void batchUpdateWord(ExhibitQueryParam param);

	/**
	 * 批量新建文字存量信息
	 * @param param
	 */
	void batchCreateWord(ExhibitQueryParam param);

}
