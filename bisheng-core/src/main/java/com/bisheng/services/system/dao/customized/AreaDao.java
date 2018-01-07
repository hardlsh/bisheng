package com.bisheng.services.system.dao.customized;

import java.util.List;

import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.services.system.dao.generated.AreaMapper;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;

public interface AreaDao extends AreaMapper{
	
	/**
	 * 获取地区列表
	 */
	List<Area> getAreaList();

	/**
	 * 查询地区列表
	 */
	List<AreaModel> getAreaListByParam(AreaParam param);

}
