package com.bisheng.services.exhibit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.ExhibitDao;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.bisheng.services.exhibit.service.ExhibitService;
import com.github.pagehelper.PageInfo;

@Service
public class ExhibitServiceImpl extends BaseService implements ExhibitService {

	private ExhibitDao getExhibitDao(){
		return sqlSession.getMapper(ExhibitDao.class);
	}
	
	@Override
	public List<ExhibitModel> queryExhibitListByParam(ExhibitQueryParam exhibitParam) {
		return getExhibitDao().queryExhibitListByParam(exhibitParam);
	}

	@Override
	public PageInfo<ExhibitModel> queryPagedExhibitListByParam(ExhibitQueryParam param) {
		setStartPage(param);
		List<ExhibitModel> list = getExhibitDao().queryExhibitListByParam(param);
		PageInfo<ExhibitModel> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<ExhibitModel> queryAllExhibitByParam(ExhibitQueryParam exhibitParam) {
		return getExhibitDao().queryAllExhibitByParam(exhibitParam);
	}

	@Override
	public Exhibit queryExhibitById(Long exhibitId) {
		return getExhibitDao().selectByPrimaryKey(exhibitId);
	}

	@Override
	public List<ExhibitModel> checkExhibitByParam(ExhibitQueryParam param) {
		return getExhibitDao().checkExhibitByParam(param);
	}

	@Override
	public void addExhibitReturnId(Exhibit exhibit) {
		getExhibitDao().addExhibitReturnId(exhibit);
	}

	@Override
	public void updateExhibitById(Exhibit exhibit) {
		getExhibitDao().updateExhibitById(exhibit);
	}

	@Override
	public int deleteExhibitById(Exhibit exhibit){
		return getExhibitDao().deleteExhibitById(exhibit);
	}

}
