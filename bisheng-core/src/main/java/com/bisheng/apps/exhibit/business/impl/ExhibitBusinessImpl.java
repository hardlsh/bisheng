package com.bisheng.apps.exhibit.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.bisheng.services.exhibit.service.ExhibitService;
import com.github.pagehelper.PageInfo;

@Service
public class ExhibitBusinessImpl implements ExhibitBusiness {

	@Resource
	private ExhibitService  exhibitService;
	
	@Override
	public List<ExhibitModel> queryExhibitListByParam(ExhibitQueryParam exhibitParam) {
		return exhibitService.queryExhibitListByParam(exhibitParam);
	}

	@Override
	public PageInfo<ExhibitModel> queryPagedExhibitListByParam(ExhibitQueryParam param) {
		return exhibitService.queryPagedExhibitListByParam(param);
	}
	
	@Override
	public List<ExhibitModel> queryAllExhibitByParam(ExhibitQueryParam exhibitParam) {
		return exhibitService.queryAllExhibitByParam(exhibitParam);
	}

	@Override
	public List<ExhibitModel> checkExhibitByParam(ExhibitQueryParam param) {
		return exhibitService.checkExhibitByParam(param);
	}

	@Override
	public void addExhibit(ExhibitQueryParam param) {
		Exhibit exhibit = new Exhibit();
		MBeanUtil.copyProperties(param, exhibit);
		exhibit.setStatus(param.getExhibitStatus());
		exhibitService.addExhibitReturnId(exhibit);
	}

	@Override
	public void updateExhibit(ExhibitQueryParam param) {
		Exhibit exhibit = new Exhibit();
		MBeanUtil.copyProperties(param, exhibit);
		exhibit.setStatus(param.getExhibitStatus());
		exhibitService.updateExhibitById(exhibit);
	}

}
