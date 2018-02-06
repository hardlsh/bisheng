package com.bisheng.apps.exhibit.business.impl;

import java.util.List;

import javax.annotation.Resource;

import com.bisheng.core.framework.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bisheng.apps.exhibit.business.BoothBusiness;
import com.bisheng.apps.exhibit.business.WordBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.core.framework.exception.Preconditions;
import com.bisheng.services.exhibit.enums.BoothWordSignEnum;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.generated.Booth;
import com.bisheng.services.exhibit.model.generated.BoothWord;
import com.bisheng.services.exhibit.service.BoothService;
import com.bisheng.services.exhibit.service.BoothWordService;
import com.github.pagehelper.PageInfo;

@Service
public class BoothBusinessImpl implements BoothBusiness {

	private static final Logger logger = LoggerFactory.getLogger(BoothBusinessImpl.class);

	@Resource
	private BoothService boothService;
	@Resource
	private BoothWordService boothWordService;
	@Autowired
	private WordBusiness wordBusiness;

	@Override
	public List<BoothModel> queryBoothListByParam(ExhibitQueryParam exhibitParam) {
		List<BoothModel> boothList = boothService.queryBoothListByParam(exhibitParam);
		return boothList;
	}
	
	@Override
	public PageInfo<BoothModel> queryPagedBoothByParam(ExhibitQueryParam param) {
		PageInfo<BoothModel> pageInfo = boothService.queryPagedBoothByParam(param);
		return pageInfo;
	}
	
	@Override
	public void addBooth(ExhibitQueryParam param) {
		Booth booth = new Booth();
		MBeanUtil.copyProperties(param, booth);
		booth.setStatus(param.getBoothStatus());
		boothService.addBoothReturnId(booth);
	}

	@Override
	public int updateBooth(ExhibitQueryParam param) {
		Booth booth = new Booth();
		MBeanUtil.copyProperties(param, booth);
		booth.setStatus(param.getBoothStatus());
		return boothService.updateSelectiveByPrimaryKey(booth);
	}

	@Override
	public void deleteBooth(ExhibitQueryParam param) {
		Booth record = new Booth();
		record.setBoothId(param.getBoothId());
		int result = boothService.deleteBoothById(record);
		if (result != 1) {
			throw new BusinessException("删除展位异常");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void batchInsertBoothWord(ExhibitQueryParam queryParam) {
		Booth booth = new Booth();
		booth.setBoothId(queryParam.getBoothId());
		booth.setWordContent(queryParam.getWordContent());
		if (BoothWordSignEnum.NOIMPORT.getKey() == queryParam.getWordSign()) {
			booth.setWordSign(BoothWordSignEnum.ALIMPORT.getKey());
		}
		int updateIndex = boothService.updateSelectiveByPrimaryKey(booth);
		Preconditions.checkArgument(updateIndex == 1, "修改导入标识失败");
		Booth queryBooth = boothService.queryBoothById(queryParam.getBoothId());
		queryParam.setExhibitId(queryBooth.getExhibitId());
		queryParam.setExhibitName(queryBooth.getExhibitName());
		
		// 之前已经导入过的,需要先删除
		if (BoothWordSignEnum.ALIMPORT.getKey() == queryParam.getWordSign()) {
			BoothWord boothWord = new BoothWord();
			boothWord.setBoothId(queryParam.getBoothId());
			int delIndex = boothWordService.deleteByBoothId(boothWord);
			Preconditions.checkArgument(delIndex >= 1, "删除原来导入数据失败");
		}
		boothWordService.batchInsert(queryParam.getBoothWordList());
		
		// 插入文字存量数据和文字入库数据
		wordBusiness.batchInsertWord(queryParam);
	}

	@Override
	public PageInfo<BoothWordModel> queryPagedBoothWordByParam(ExhibitQueryParam param) {
		return boothWordService.queryPagedBoothWordByParam(param);
	}
	
}
