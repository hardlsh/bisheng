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
import com.bisheng.services.exhibit.enums.WordOperateTypeEnum;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordOperate;
import com.bisheng.services.exhibit.service.WordOperateService;
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
	private WordOperateService wordOperateService;
	
	@Override
	public PageInfo<WordModel> queryPagedWordByParam(ExhibitQueryParam param) {
		param.setOperateType(WordOperateTypeEnum.IN.getKey());
		PageInfo<WordModel> inWordPageList = wordService.queryPagedWordByParam(param);
		param.setOperateType(WordOperateTypeEnum.OUT.getKey());
		PageInfo<WordModel> outWordPageList = wordService.queryPagedWordByParam(param);
		List<WordModel> inWordList = inWordPageList.getList();
		List<WordModel> outWordList = outWordPageList.getList();
		for (WordModel inWord : inWordList) {
			inWord.setInTotalCount(inWord.getOperateCount());
			for (WordModel outWord : outWordList) {
				if (inWord.getWordId().equals(outWord.getWordId())) {
					inWord.setOutTotalCount(outWord.getOperateCount());
					break;
				}
			}
		}
		return inWordPageList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void batchInsertWord(ExhibitQueryParam param) {
		Word word;
		WordModel wordModel;
		WordOperate wordOperate;
		List<WordOperate> wordOperateList = new ArrayList<WordOperate>();
		for (BoothWordModel boothWord : param.getBoothWordList()) {
			wordModel = new WordModel();
			wordModel.setWord(boothWord.getWord());
			List<Word> wordList = wordService.queryWordList(wordModel);
			if (null == wordList || wordList.isEmpty()) {
				word = new Word();
				word.setWord(boothWord.getWord());
				word.setExhibitId(param.getExhibitId());
				word.setTotalCount(param.getTemplateCount());
				wordService.addWordReturnId(word);
				
				wordOperate = new WordOperate();
				wordOperate.setExhibitId(param.getExhibitId());
				wordOperate.setWordId(word.getWordId());
				wordOperate.setType(WordOperateTypeEnum.IN.getKey());
				wordOperate.setCount(param.getTemplateCount());
				wordOperate.setOperateUser(param.getUpdateByUser());
				wordOperateList.add(wordOperate);
			} else if (null != wordList && wordList.size() == 1){
				wordOperate = new WordOperate();
				wordOperate.setExhibitId(param.getExhibitId());
				wordOperate.setWordId(wordList.get(0).getWordId());
				wordOperate.setType(WordOperateTypeEnum.IN.getKey());
				wordOperate.setCount(param.getTemplateCount());
				wordOperate.setOperateUser(param.getUpdateByUser());
				wordOperateList.add(wordOperate);
			} else {
				logger.error("【文字存量业务】文字存量表数据记录有重复,对应展馆ID:"+boothWord.getExhibitId()+",对应文字:" + boothWord.getWord());
			}
		}
		wordOperateService.batchInsert(wordOperateList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void batchUpdateWord(ExhibitQueryParam param) {
		Map<Long, Word> wordMap = getWordMap(param);
		Word word;
		WordOperate wordOperate;
		Long totalCount;
		List<WordOperate> wordOperateList = new ArrayList<WordOperate>();
		for (Long wordId : param.getWordIdList()) {
			word = wordMap.get(wordId);
			if (WordOperateTypeEnum.IN.getKey() == param.getOperateType().intValue()) {
				word.setTotalCount(word.getTotalCount() + param.getCount());
				word.setInDate(param.getUpdateDate());
			} else if (WordOperateTypeEnum.OUT.getKey() == param.getOperateType().intValue()){
				totalCount = word.getTotalCount() - param.getCount();
				if (totalCount < 0) {
					logger.error("【文字存量业务】业务异常,售出数量不得大于现有存量,对应展馆Id:" 
							+ param.getExhibitId() + ",对应文字Id:" + wordId);
					throw new BusinessException("业务异常,售出数量不得大于现有存量");
				}
				word.setTotalCount(totalCount);
				word.setOutDate(param.getUpdateDate());
			} else {
				logger.error("【文字存量业务】业务异常,操作类型不存在");
				throw new BusinessException("系统异常");
			}
			wordService.updateWord(word);
			
			wordOperate = new WordOperate();
			wordOperate.setExhibitId(param.getExhibitId());
			wordOperate.setWordId(wordId);
			wordOperate.setType(param.getOperateType());
			wordOperate.setCount(param.getCount());
			wordOperate.setOperateDate(param.getUpdateDate());
			wordOperate.setOperateUser(param.getUpdateByUser());
			wordOperateList.add(wordOperate);
		}
		wordOperateService.batchInsert(wordOperateList);
	}
	
	private Map<Long, Word> getWordMap(ExhibitQueryParam param){
		WordModel wordModel = new WordModel();
		wordModel.setExhibitId(param.getExhibitId());
		wordModel.setWordIdList(param.getWordIdList());
		List<Word> wordList = wordService.queryWordList(wordModel);
		Map<Long, Word> wordMap = Maps.uniqueIndex(wordList, new Function<Word, Long>() {
            public Long apply(Word from) {
                return from.getWordId();
            }
        });
		return wordMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void batchCreateWord(ExhibitQueryParam param) {
		Word word;
		WordModel wordModel;
		WordOperate wordOperate;
		List<WordOperate> wordOperateList = new ArrayList<WordOperate>();
		for (String wordSingle : param.getWordList()) {
			wordModel = new WordModel();
			wordModel.setWord(wordSingle);
			List<Word> wordList = wordService.queryWordList(wordModel);
			if (null == wordList || wordList.isEmpty()) {
				word = new Word();
				word.setWord(wordSingle);
				word.setExhibitId(param.getExhibitId());
				word.setTotalCount(param.getCount());
				wordService.addWordReturnId(word);
				
				wordOperate = new WordOperate();
				wordOperate.setExhibitId(param.getExhibitId());
				wordOperate.setWordId(word.getWordId());
				wordOperate.setType(WordOperateTypeEnum.IN.getKey());
				wordOperate.setCount(param.getCount());
				wordOperate.setOperateUser(param.getUpdateByUser());
				wordOperateList.add(wordOperate);
			} else if (null != wordList && wordList.size() == 1){
				wordOperate = new WordOperate();
				wordOperate.setExhibitId(param.getExhibitId());
				wordOperate.setWordId(wordList.get(0).getWordId());
				wordOperate.setType(WordOperateTypeEnum.IN.getKey());
				wordOperate.setCount(param.getCount());
				wordOperate.setOperateUser(param.getUpdateByUser());
				wordOperateList.add(wordOperate);
			} else {
				logger.error("【文字存量业务】文字存量表数据记录有重复,对应展馆ID:"+param.getExhibitId()+",对应文字:" + wordSingle);
			}
		}
		wordOperateService.batchInsert(wordOperateList);
	}

}
