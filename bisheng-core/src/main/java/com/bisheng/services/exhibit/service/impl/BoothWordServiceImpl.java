package com.bisheng.services.exhibit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.BoothWordDao;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.generated.BoothWord;
import com.bisheng.services.exhibit.service.BoothWordService;
import com.github.pagehelper.PageInfo;

@Service
public class BoothWordServiceImpl extends BaseService implements BoothWordService {

	private BoothWordDao getBoothWordDao(){
		return sqlSession.getMapper(BoothWordDao.class);
	}

	@Override
	public void batchInsert(List<BoothWordModel> boothWordList) {
		if (null != boothWordList && !boothWordList.isEmpty()) {
			Map<String, Object> map = new HashMap<>();
			map.put("boothWordList", boothWordList);
			getBoothWordDao().batchInsert(map);
		}
	}

	@Override
	public int deleteByBoothId(BoothWord boothWord) {
		return getBoothWordDao().deleteByBoothId(boothWord);
	}

	@Override
	public PageInfo<BoothWordModel> queryPagedBoothWordByParam(ExhibitQueryParam param) {
		setStartPage(param);
		List<BoothWordModel> list = getBoothWordDao().queryBoothWordByParam(param);
		PageInfo<BoothWordModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
