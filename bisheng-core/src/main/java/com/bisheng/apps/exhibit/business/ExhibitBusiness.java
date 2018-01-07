package com.bisheng.apps.exhibit.business;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.github.pagehelper.PageInfo;

/**
 * 展馆业务类
 * 
 * @author lihao
 */
public interface ExhibitBusiness {
	
	/**
	 * 根据展馆查询参数查询展馆列表(关联用户表)
	 * @param exhibitParam
	 * @return
	 */
	List<ExhibitModel> queryExhibitListByParam(ExhibitQueryParam exhibitParam);

	/**
	 * 分页查询展馆列表(关联用户表)
	 * @param exhibitParam
	 * @return
	 */
	PageInfo<ExhibitModel> queryPagedExhibitListByParam(ExhibitQueryParam param);
	
	/**
	 * 查询全部展馆(没有关联用户表)
	 */
	List<ExhibitModel> queryAllExhibitByParam(ExhibitQueryParam exhibitParam);
	
	/**
	 * 校验展馆信息
	 */
	List<ExhibitModel> checkExhibitByParam(ExhibitQueryParam param);

	/**
	 * 新增展馆
	 */
	void addExhibit(ExhibitQueryParam param);

	/**
	 * 修改展馆
	 */
	void updateExhibit(ExhibitQueryParam param);

}
