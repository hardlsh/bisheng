package com.bisheng.services.exhibit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.WordOperateDao;
import com.bisheng.services.exhibit.model.generated.WordOperate;
import com.bisheng.services.exhibit.service.WordOperateService;

@Service
public class WordOperateServiceImpl extends BaseService implements WordOperateService{
	
	private WordOperateDao getWordOperateDao(){
		return sqlSession.getMapper(WordOperateDao.class);
	}

	@Override
	public void batchInsert(List<WordOperate> wordOperateList) {
		Map<String, Object> map = new HashMap<>();
		map.put("wordOperateList", wordOperateList);	
		getWordOperateDao().batchInsert(map);
	}
	
}
