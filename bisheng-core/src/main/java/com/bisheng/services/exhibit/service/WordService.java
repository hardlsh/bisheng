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
	 * 查询入库信息
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordInByParam(ExhibitQueryParam param);

	/**
	 * 查询出库信息
	 * @param param
	 * @return
	 */
	PageInfo<WordModel> queryPagedWordOutByParam(ExhibitQueryParam param);

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

	/**
	 * 删除文字存量
	 * @param exhibitId
	 */
	void deleteByExhibitId(Long exhibitId);
}
