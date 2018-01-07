package com.bisheng.apps.system.business;

import java.util.List;

import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.system.model.customized.DictItemModel;
import com.bisheng.services.system.model.generated.Dict;
import com.bisheng.services.system.model.generated.DictItem;

/**
 * 字典业务类
 * 
 * @author lihao
 */
public interface DictBusiness {

	/**
	 * 查询字典列表
	 * 
	 * @param query null 代表查询所有
	 * @return
	 */
	public PaginationResult<List<Dict>> queryDictList(DictQuery query);
	
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
	 * 查询字典项所有值
	 * 
	 * @param dictCode
	 * @return
	 */
	public List<DictItem> queryDictItemList(String dictCode);

	public String queryDictItemString(String dictCode);
	
	/**
	 * 获取字典项值
	 * 
	 * @param dictCode
	 * @return
	 */
	public String getDictItemName(String dictCode, String itemCode);
	
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
	
}
