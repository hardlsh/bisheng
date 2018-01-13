package com.bisheng.services.exhibit.service;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.github.pagehelper.PageInfo;

/**
 * 展馆服务类
 * 
 * @author lihao
 */
public interface ExhibitService {
	
	/**
	 * 根据展馆查询参数查询展馆列表(关联用户表)
	 * @param exhibitParam
	 * @return
	 */
	List<ExhibitModel> queryExhibitListByParam(ExhibitQueryParam exhibitParam);

	/**
	 * 分页查询展馆列表(关联用户表)
	 * @param param
	 * @return
	 */
	PageInfo<ExhibitModel> queryPagedExhibitListByParam(ExhibitQueryParam param);
	
	/**
	 * 查询全部展馆(没有关联用户表)
	 */
	List<ExhibitModel> queryAllExhibitByParam(ExhibitQueryParam exhibitParam);

	/**
	 * 查询展馆
	 * @param exhibitId
	 * @return
	 */
	Exhibit queryExhibitById(Long exhibitId);
	
	/**
	 * 校验展馆信息
	 */
	List<ExhibitModel> checkExhibitByParam(ExhibitQueryParam param);

	/**
	 * 新增展馆,并返回主键
	 */
	void addExhibitReturnId(Exhibit exhibit);

	/**
	 * 根据主键id, 修改展馆
	 */
	void updateExhibitById(Exhibit exhibit);

	/**
	 * 根据主键id, 删除展馆
	 * @param exhibit
	 * @return
	 */
	int deleteExhibitById(Exhibit exhibit);
}
