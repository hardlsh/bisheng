package com.bisheng.services.exhibit.dao.customized;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.dao.generated.ExhibitMapper;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;

public interface ExhibitDao extends ExhibitMapper{
	
	/**
	 * 根据展馆查询参数查询展馆列表(关联用户表)
	 * @param exhibitParam
	 * @return
	 */
	List<ExhibitModel> queryExhibitListByParam(ExhibitQueryParam exhibitParam);

	/**
	 * 查询展馆列表,单表查询
	 */
	List<ExhibitModel> queryAllExhibitByParam(ExhibitQueryParam exhibitParam);
	
	/**
	 * 校验展馆信息
	 */
	List<ExhibitModel> checkExhibitByParam(ExhibitQueryParam param);

	/**
	 * 插入展馆,并返回主键
	 */
	void addExhibitReturnId(Exhibit exhibit);
	
	/**
	 * 根据主键id, 修改展馆
	 */
	void updateExhibitById(Exhibit exhibit);
	
}
