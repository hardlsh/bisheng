package com.bisheng.services.exhibit.service;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;
import com.github.pagehelper.PageInfo;

/**
 * 文字存量
 * 
 * @author lihao
 */
public interface WordService {
	/**
	 * 根据参数查询文字存量记录(关联展馆表)
	 * @param queryParam
	 * @return
	 */
	List<WordModel> queryWordListByParam(ExhibitQueryParam queryParam);
	
	/**
	 * 分页查询文字存量记录(关联展馆表)
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordByParam(ExhibitQueryParam param);

	/**
	 * 分页查询文字操作信息汇总
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordOperateCount(ExhibitQueryParam param);
	
	/**
	 * 查询文字存量记录(单表查询)
	 * @param wordModel
	 * @return
	 */
	List<Word> queryWordList(WordModel wordModel);
	
	/**
	 * 插入返回主键
	 * @param word
	 */
	void addWordReturnId (Word word);

	/**
	 * 单条修改
	 * @param word
	 */
	void updateWord(Word word);

}
