package com.bisheng.services.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.AreaDao;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;
import com.bisheng.services.system.service.AreaService;

@Service
public class AreaServiceImpl extends BaseService implements AreaService {
	private AreaDao getAreaDao(){
		return sqlSession.getMapper(AreaDao.class);
	}

	@Override
	public List<Area> getAllAreaList() {
		return getAreaDao().getAreaList();
	}

	@Override
	public List<AreaModel> getAreaListByParam(AreaParam param) {
		return getAreaDao().getAreaListByParam(param);
	}
	
	@Override
	public int insertArea(Area area) {
		return getAreaDao().insertSelective(area);
	}

	@Override
	public int updateAreaById(Area area) {
		return getAreaDao().updateByPrimaryKeySelective(area);
	}

	@Override
	public int deleteAreaById(String areaId) {
		return getAreaDao().deleteByPrimaryKey(areaId);
	}
	
}
