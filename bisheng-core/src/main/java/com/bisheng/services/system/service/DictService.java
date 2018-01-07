package com.bisheng.services.system.service;

import java.util.List;

import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.services.system.model.customized.DictItemModel;
import com.bisheng.services.system.model.generated.Dict;
import com.bisheng.services.system.model.generated.DictItem;
import com.github.pagehelper.PageInfo;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月2日　下午6:01:19
 * @version 1.0
 * @since JDK 1.8
 */
public interface DictService {

	/**
	 * 查询字典列表
	 * 
	 * @return
	 */
	public PageInfo<Dict> queryDictList(DictQuery query);
	
	/**
	 * 新增数据字典项
	 * 
	 * @param dict
	 */
	public void addDict(Dict dict);
	
	/**
	 * 修改数据字典项
	 * 
	 * @param dict
	 */
	public void updateDict(Dict dict);
	
	/**
	 * 查询字典项值
	 * 
	 * @param query
	 * @return
	 */
	public List<DictItem> queryDictItemList(DictItemQuery query);
	
	/**
	 * 新增字典项值
	 * 
	 * @param dict
	 */
	public void addDictItem(DictItem dictItem);
	
	/**
	 * 修改字典项值
	 * 
	 * @param dict
	 */
	public void updateDictItem(DictItemModel dictItemModel);
	
	/**
	 * 查询字典列表
	 * 
	 * @return
	 */
	public DictItem queryDictItem(String dictCode,String itemCode);
}
