package com.bisheng.services.exhibit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.BoothDao;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.generated.Booth;
import com.bisheng.services.exhibit.service.BoothService;
import com.github.pagehelper.PageInfo;

@Service
public class BoothServiceImpl extends BaseService implements BoothService {

	private BoothDao getBoothDao(){
		return sqlSession.getMapper(BoothDao.class);
	}

	@Override
	public List<BoothModel> queryBoothListByParam(ExhibitQueryParam exhibitParam) {
		return getBoothDao().queryBoothListByParam(exhibitParam);
	}
	
	@Override
	public PageInfo<BoothModel> queryPagedBoothByParam(ExhibitQueryParam param) {
		setStartPage(param);
		List<BoothModel> list = getBoothDao().queryBoothListByParam(param);
		PageInfo<BoothModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public Booth queryBoothById(Long boothId) {
		return getBoothDao().selectByPrimaryKey(boothId);
	}
	
	@Override
	public void addBoothReturnId(Booth booth) {
		getBoothDao().addBoothReturnId(booth);
	}

	@Override
	public int updateSelectiveByPrimaryKey(Booth booth) {
		return getBoothDao().updateByPrimaryKeySelective(booth);
	}
	
}
