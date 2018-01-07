package com.bisheng.services.system.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.UserExhibitDao;
import com.bisheng.services.system.model.generated.UserExhibitExample;
import com.bisheng.services.system.service.UserExhibitService;

@Service
public class UserExhibitServiceImpl extends BaseService implements UserExhibitService {

	private UserExhibitDao getUserExhibitDao() {
		return sqlSession.getMapper(UserExhibitDao.class);
	}

	@Override
	public int deleteByExample(UserExhibitExample example) {
		return getUserExhibitDao().deleteByExample(example);
	}

	@Override
	public int batchInsert(Map<String, Object> addUserExhibitMap) {
		return getUserExhibitDao().batchInsert(addUserExhibitMap);
	}

}
