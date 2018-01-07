package com.bisheng.services.system.service;

import java.util.List;

import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;

/**
 * 地区服务类
 * 
 * @author lihao
 */
public interface AreaService {
	
	/**
	 * 获取所有地区列表
	 */
	List<Area> getAllAreaList();
	
	/**
	 * 查询地区列表
	 */
	List<AreaModel> getAreaListByParam(AreaParam param);

	/**
	 * 新增地区
	 */
	int insertArea(Area area);

	/**
	 * 根据id,修改地区
	 */
	int updateAreaById(Area area);

	/**
	 * 根据id,删除地区
	 */
	int deleteAreaById(String areaId);

}
