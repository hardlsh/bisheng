package com.bisheng.services.exhibit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.WordDao;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.service.WordService;
import com.github.pagehelper.PageInfo;

@Service
public class WordServiceImpl extends BaseService implements WordService {

	private WordDao getWordDao(){
		return sqlSession.getMapper(WordDao.class);
	}

	@Override
	public PageInfo<WordModel> queryPagedWordInByParam(ExhibitQueryParam param) {
		setStartPage(param);
		List<WordModel> list = getWordDao().queryWordInByParam(param);
		PageInfo<WordModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<WordModel> queryPagedWordOutByParam(ExhibitQueryParam param) {
		setStartPage(param);
		List<WordModel> list = getWordDao().queryWordOutByParam(param);
		PageInfo<WordModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Word> queryWordList(WordModel wordModel) {
		return getWordDao().queryWordList(wordModel);
	}

	@Override
	public void addWordReturnId (Word word) {
		getWordDao().addWordReturnId(word);
	}

	@Override
	public void updateWord(Word word) {
		Word record = new Word();
		record.setWordId(word.getWordId());
		record.setTotalCount(word.getTotalCount());
		if (null != word.getInDate()) {
			record.setInDate(word.getInDate());
		}
		if (null != word.getOutDate()) {
			record.setOutDate(word.getOutDate());
		}
		getWordDao().updateWord(record);
	}


}
