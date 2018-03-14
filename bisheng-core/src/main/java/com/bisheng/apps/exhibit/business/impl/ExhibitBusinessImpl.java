package com.bisheng.apps.exhibit.business.impl;

import com.bisheng.apps.exhibit.business.BoothBusiness;
import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.business.WordBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.core.framework.exception.BusinessException;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.bisheng.services.exhibit.service.BoothService;
import com.bisheng.services.exhibit.service.ExhibitService;
import com.bisheng.services.system.service.UserExhibitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExhibitBusinessImpl implements ExhibitBusiness {

	@Autowired
	private ExhibitService exhibitService;
	@Autowired
	private BoothBusiness boothBusiness;
	@Autowired
	private BoothService boothService;
	@Autowired
	private UserExhibitService userExhibitService;
	@Autowired
	private WordBusiness wordBusiness;
	
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteExhibit(ExhibitQueryParam param){
		Exhibit record = new Exhibit();
		record.setExhibitId(param.getExhibitId());
		int result = exhibitService.deleteExhibitById(record);
		if (result != 1) {
			throw new BusinessException("删除展馆异常");
		}
		// 删除展位相关信息
		BoothModel boothModel = new BoothModel();
		boothModel.setExhibitId(param.getExhibitId());
		List<BoothModel> boothModelList = boothService.queryBoothModelByModel(boothModel);
		if (null != boothModelList && boothModelList.size() > 0) {
			for (BoothModel booth : boothModelList) {
				param.setBoothId(booth.getBoothId());
				boothBusiness.deleteBooth(param);
			}
		}
		// 删除文字信息
		wordBusiness.deleteWordByExhibitId(param.getExhibitId());
		// 删除用户展馆信息
		userExhibitService.deleteByExhibitId(param.getExhibitId());
	}
}
