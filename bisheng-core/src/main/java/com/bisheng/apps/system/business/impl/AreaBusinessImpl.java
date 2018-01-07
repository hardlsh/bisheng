package com.bisheng.apps.system.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisheng.apps.system.business.AreaBusiness;
import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;
import com.bisheng.services.system.service.AreaService;
import com.google.common.base.Preconditions;

@Service
public class AreaBusinessImpl implements AreaBusiness {
	//创建日志对象
//	private static final Logger logger = LoggerFactory.getLogger(AreaBusinessImpl.class);
	
	@Autowired
	private AreaService areaService;

	@Override
	public List<Area> getAllAreaList() {
		return areaService.getAllAreaList();
	}

	@Override
	public List<AreaModel> getAreaListByParam(AreaParam param) {
		return areaService.getAreaListByParam(param);
	}

	@Override
	public void insertArea(Area area) {
		int index = areaService.insertArea(area);
		Preconditions.checkArgument(index == 1, "插入地区失败");
	}

	@Override
	public void updateArea(Area area) {
		int index = areaService.updateAreaById(area);
		Preconditions.checkArgument(index == 1, "修改地区失败");
	}

	@Override
	public void deleteArea(Area area) {
		int index = areaService.deleteAreaById(area.getAreaId());
		Preconditions.checkArgument(index == 1, "删除地区失败");
	}

}
