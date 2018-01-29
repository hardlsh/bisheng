package com.bisheng.services.exhibit.dao.customized;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.dao.generated.WordMapper;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;

public interface WordDao extends WordMapper{

	/**
	 * 查询文字入库信息
	 * @param param
	 * @return
	 */
	List<WordModel> queryWordInByParam(ExhibitQueryParam param);

	/**
	 * 查询文字存量列表
	 * @param queryParam
	 * @return
	 */
	List<WordModel> queryWordListByParam(ExhibitQueryParam queryParam);

	/**
	 * 单表查询
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
	 * 根据主键进行修改
	 * @param word
	 * @return
	 */
	int updateWord(Word word);

}
