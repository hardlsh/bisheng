package com.bisheng.apps.system.business;

import java.util.List;

import com.bisheng.apps.system.param.AreaParam;
import com.bisheng.services.system.model.customized.AreaModel;
import com.bisheng.services.system.model.generated.Area;

/**
 * 地区业务类
 * 
 * @author lihao
 */
public interface AreaBusiness {
	
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
	void insertArea(Area area);

	/**
	 * 修改地区
	 */
	void updateArea(Area area);

	/**
	 * 删除地区
	 */
	void deleteArea(Area area);

}
