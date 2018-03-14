package com.bisheng.services.system.service.impl;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.UserExhibitDao;
import com.bisheng.services.system.model.generated.UserExhibitExample;
import com.bisheng.services.system.service.UserExhibitService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserExhibitServiceImpl extends BaseService implements UserExhibitService {

	private UserExhibitDao getUserExhibitDao() {
		return sqlSession.getMapper(UserExhibitDao.class);
	}

	@Override
	public int deleteByUserId(Long userId) {
		UserExhibitExample example = new UserExhibitExample();
		UserExhibitExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return getUserExhibitDao().deleteByExample(example);
	}

	@Override
	public int deleteByExhibitId(Long exhibitId) {
		UserExhibitExample example = new UserExhibitExample();
		UserExhibitExample.Criteria criteria = example.createCriteria();
		criteria.andExhibitIdEqualTo(exhibitId);
		return getUserExhibitDao().deleteByExample(example);
	}

	@Override
	public int batchInsert(Map<String, Object> addUserExhibitMap) {
		return getUserExhibitDao().batchInsert(addUserExhibitMap);
	}

}
