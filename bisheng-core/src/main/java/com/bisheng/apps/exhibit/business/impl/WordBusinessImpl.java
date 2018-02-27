package com.bisheng.apps.exhibit.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bisheng.apps.exhibit.business.WordBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordIn;
import com.bisheng.services.exhibit.service.WordInService;
import com.bisheng.services.exhibit.service.WordService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

@Service
public class WordBusinessImpl implements WordBusiness {
	
	private static final Logger logger = LoggerFactory.getLogger(WordBusinessImpl.class);
	
	@Autowired
	private WordService wordService;
	@Autowired
	private WordInService wordInService;

	@Override
	public PageInfo<WordModel> queryPagedWordInByParam(ExhibitQueryParam param) {
		PageInfo<WordModel> inWordPageList = wordService.queryPagedWordInByParam(param);
		return inWordPageList;
	}

	private void convertWordList (List<WordModel> inWordList, List<WordModel> outWordList) {
		for (WordModel inWord : inWordList) {
			inWord.setInTotalCount(inWord.getOperateCount());
			for (WordModel outWord : outWordList) {
				if (inWord.getWordId().equals(outWord.getWordId())) {
					inWord.setOutTotalCount(outWord.getOperateCount());
					break;
				}
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void newWordIn(ExhibitQueryParam param) {
		Word word;
		WordModel wordModel;
		WordIn wordIn;
		List<WordIn> wordInList = new ArrayList<>();
		for (BoothWordModel boothWord : param.getBoothWordList()) {
			wordModel = new WordModel();
			wordModel.setWord(boothWord.getWord());
			List<Word> wordList = wordService.queryWordList(wordModel);
			if (null == wordList || wordList.isEmpty()) {
				word = new Word();
				word.setExhibitId(param.getExhibitId());
				word.setWord(boothWord.getWord());
				word.setInDate(param.getUpdateDate());
				word.setTotalCount(param.getTemplateCount());
				wordService.addWordReturnId(word);
				wordIn = assembleWordIn(param);
				wordIn.setWordId(word.getWordId());
				wordIn.setWord(word.getWord());
				wordInList.add(wordIn);
			} else if (null != wordList && wordList.size() == 1){
				word = wordList.get(0);
				word.setTotalCount(word.getTotalCount() + param.getTemplateCount());
				word.setInDate(param.getUpdateDate());
				wordService.updateWord(word);
				wordIn = assembleWordIn(param);
				wordIn.setWordId(wordList.get(0).getWordId());
				wordIn.setWord(wordList.get(0).getWord());
				wordInList.add(wordIn);
			} else {
				logger.error("【文字存量业务】文字存量表数据记录有重复,对应展馆ID:"+boothWord.getExhibitId()+",对应文字:" + boothWord.getWord());
			}
		}
		wordInService.batchInsert(wordInList);
	}

	private WordIn assembleWordIn(ExhibitQueryParam param){
		WordIn wordIn = new WordIn();
		wordIn.setExhibitId(param.getExhibitId());
		wordIn.setExhibitName(param.getExhibitName());
		if (null != param.getTemplateCount()) {
			wordIn.setInNumber(param.getTemplateCount());
		} else if (null != param.getCount()) {
			wordIn.setInNumber(param.getCount());
		}
		wordIn.setInDate(param.getUpdateDate());
		wordIn.setInUser(param.getUpdateByUser());
		return wordIn;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateWordIn(ExhibitQueryParam param) {
		Map<Long, Word> wordMap = getWordMap(param);
		Word word;
		WordIn wordIn;
		List<WordIn> wordInList = new ArrayList<>();
		for (Long wordId : param.getWordIdList()) {
			word = wordMap.get(wordId);
			word.setTotalCount(word.getTotalCount() + param.getCount());
			word.setInDate(param.getUpdateDate());
			wordService.updateWord(word);

			wordIn = assembleWordIn(param);
			wordIn.setWordId(word.getWordId());
			wordIn.setWord(word.getWord());
			wordInList.add(wordIn);
		}
		wordInService.batchInsert(wordInList);
	}
	
	private Map<Long, Word> getWordMap(ExhibitQueryParam param){
		WordModel wordModel = new WordModel();
		wordModel.setExhibitId(param.getExhibitId());
		wordModel.setWordIdList(param.getWordIdList());
		List<Word> wordList = wordService.queryWordList(wordModel);
		Map<Long, Word> wordMap = Maps.uniqueIndex(wordList, new Function<Word, Long>() {
            @Override
			public Long apply(Word from) {
                return from.getWordId();
            }
        });
		return wordMap;
	}

}
