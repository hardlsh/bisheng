package com.bisheng.services.exhibit.service;

import java.util.List;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.generated.Booth;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.github.pagehelper.PageInfo;

/**
 * 展位服务类
 * 
 * @author lihao
 */
public interface BoothService {
	
	/**
	 * 查询参数查询展位列表
	 */
	List<BoothModel> queryBoothListByParam(ExhibitQueryParam exhibitParam);
	
	/**
	 * 分页查询展位列表
	 */
	PageInfo<BoothModel> queryPagedBoothByParam(ExhibitQueryParam param);

	/**
	 * 根据展位Id查询(单表)
	 */
	Booth queryBoothById(Long boothId);

	/**
	 * 单表查询，验重
	 * @param boothModel
	 * @return
	 */
	List<BoothModel> queryBoothModelByModel(BoothModel boothModel);
	
	/**
	 * 新增展位,并返回主键
	 */
	void addBoothReturnId(Booth booth);
	
	/**
	 * 根据主键进行修改
	 */
	int updateSelectiveByPrimaryKey(Booth booth);

	/**
	 * 根据主键id, 删除展位
	 * @param booth
	 * @return
	 */
	int deleteBoothById(Booth booth);
}
