package com.bisheng.apps.exhibit.business;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.github.pagehelper.PageInfo;

/**
 * 展位业务类
 * 
 * @author lihao
 */
public interface BoothBusiness {
	
	/**
	 * 查询参数查询展位列表
	 * @param exhibitParam
	 * @return
	 */
	List<BoothModel> queryBoothListByParam(ExhibitQueryParam exhibitParam);

	/**
	 * 分页查询展位列表
	 * @param param
	 * @return
	 */
	PageInfo<BoothModel> queryPagedBoothByParam(ExhibitQueryParam param);
	
	/**
	 * 新增展位
	 * @param param
	 */
	void addBooth(ExhibitQueryParam param);
	
	/**
	 * 修改展位
	 */
	int updateBooth(ExhibitQueryParam param);

	/**
	 * 删除展位
	 * 删除对应展位文字、库存、出入库信息
	 * @param param
	 */
	void deleteBooth(ExhibitQueryParam param);
	
	/**
	 * 批量保存展位文字
	 */
	void batchInsertBoothWord(ExhibitQueryParam queryParam);

	/**
	 * 分页查询展位文字
	 */
	PageInfo<BoothWordModel> queryPagedBoothWordByParam(ExhibitQueryParam param);

}
