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
import com.bisheng.core.framework.exception.BusinessException;
import com.bisheng.core.framework.pager.PaginationConvert;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordIn;
import com.bisheng.services.exhibit.model.generated.WordOut;
import com.bisheng.services.exhibit.service.WordInService;
import com.bisheng.services.exhibit.service.WordOutService;
import com.bisheng.services.exhibit.service.WordService;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

@Service
public class WordBusinessImpl implements WordBusiness {
	
	private static final Logger logger = LoggerFactory.getLogger(WordBusinessImpl.class);
	
	@Autowired
	private WordService wordService;
	@Autowired
	private WordInService wordInService;
	@Autowired
	private WordOutService wordOutService;

	@Override
	public PaginationResult<List<WordModel>> queryPagedWordInByParam(ExhibitQueryParam param) {
		return PaginationConvert.buildPagination(wordService.queryPagedWordInByParam(param));
	}

	@Override
	public PaginationResult<List<WordModel>> queryPagedWordOutByParam(ExhibitQueryParam param) {
		return PaginationConvert.buildPagination(wordService.queryPagedWordOutByParam(param));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void newWordIn(ExhibitQueryParam param) {
		Word word;
		WordModel wordModel;
		WordIn wordIn;
		WordOut wordOut;
		List<WordIn> wordInList = new ArrayList<>();
		List<WordOut> wordOutList = new ArrayList<>();
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
				wordOut = assembleWordOut(param);
				wordOut.setWordId(word.getWordId());
				wordOut.setWord(word.getWord());
				wordOutList.add(wordOut);
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
		wordOutService.batchInsert(wordOutList);
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

	private WordOut assembleWordOut(ExhibitQueryParam param) {
		WordOut wordOut = new WordOut();
		wordOut.setExhibitId(param.getExhibitId());
		wordOut.setExhibitName(param.getExhibitName());
		wordOut.setOutNumber(0L);
		return wordOut;
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateWordOut(ExhibitQueryParam param) {
		Map<Long, Word> wordMap = getWordMap(param);
		Long totalCount;
		Word word;
		WordOut wordOut;
		List<WordOut> wordOutList = new ArrayList<>();
		for (Long wordId : param.getWordIdList()) {
			word = wordMap.get(wordId);
			totalCount = word.getTotalCount() - param.getCount();
			if (totalCount < 0) {
				logger.error("【文字存量业务】异常,出库数量不得大于现有库存,对应文字--" + word.getWord());
				throw new BusinessException("出库数量不得大于现有库存");
			}
			word.setTotalCount(totalCount);
			word.setOutDate(param.getUpdateDate());
			wordService.updateWord(word);

			wordOut = assembleWordOut(param);
			wordOut.setOutDate(param.getUpdateDate());
			wordOut.setOutUser(param.getUpdateByUser());
			wordOut.setWordId(word.getWordId());
			wordOut.setWord(word.getWord());
			wordOut.setOutNumber(param.getCount());
			wordOutList.add(wordOut);
		}
		wordOutService.batchInsert(wordOutList);
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
