package com.bisheng.services.exhibit.dao.customized;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.dao.generated.BoothMapper;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.generated.Booth;

public interface BoothDao extends BoothMapper{
	
	/**
	 * 查询参数查询展位列表
	 * @param param
	 * @return
	 */
	List<BoothModel> queryBoothListByParam(ExhibitQueryParam param);

	/**
	 * 单表查询,验重
	 * @param boothModel
	 * @return
	 */
	List<BoothModel>  queryBoothModelByModel(BoothModel boothModel);

	/**
	 * 新增展位,并返回主键
	 */
	void addBoothReturnId(Booth booth);
	

}
