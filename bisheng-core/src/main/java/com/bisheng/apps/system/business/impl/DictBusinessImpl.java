package com.bisheng.apps.system.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bisheng.apps.system.business.DictBusiness;
import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.core.common.util.DictJsonConverter;
import com.bisheng.core.framework.pager.PaginationConvert;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.system.model.customized.DictItemModel;
import com.bisheng.services.system.model.generated.Dict;
import com.bisheng.services.system.model.generated.DictItem;
import com.bisheng.services.system.service.DictService;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月2日　下午5:58:23
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DictBusinessImpl implements DictBusiness {

	@Resource
	private DictService dictService;
	
	@Override
	public PaginationResult<List<Dict>> queryDictList(DictQuery query) {
		return PaginationConvert.buildPagination(dictService.queryDictList(query));
	}

	@Override
	public void addDict(Dict dict) {
		dictService.addDict(dict);
	}

	@Override
	public void updateDict(Dict dict) {
		dictService.updateDict(dict);
	}

	@Override
	public List<DictItem> queryDictItemList(DictItemQuery query) {
		return dictService.queryDictItemList(query);
	}
	
	@Override
	public List<DictItem> queryDictItemList(String dictCode) {
		DictItemQuery query = new DictItemQuery();
		query.setDictCode(dictCode);
		return queryDictItemList(query);
	}

	@Override
	public String queryDictItemString(String dictCode) {
		return DictJsonConverter.buildDictJson(dictCode);
	}

	@Override
	public String getDictItemName(String dictCode, String itemCode) {
		if(itemCode == null || "".equals(itemCode.trim())){
			return null;
		}
		DictItemQuery query = new DictItemQuery();
		query.setDictCode(dictCode);
		query.setItemCode(itemCode);
		List<DictItem> list = queryDictItemList(query);
		if(list == null || list.size() == 0){
			return null;
		}
		
		return list.get(0).getItemName();
	}

	@Override
	public void addDictItem(DictItem dictItem) {
		dictService.addDictItem(dictItem);
	}

	@Override
	public void updateDictItem(DictItemModel dictItemModel) {
		dictService.updateDictItem(dictItemModel);
	}

}
