package com.bisheng.services.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.system.dao.customized.DictDao;
import com.bisheng.services.system.dao.generated.DictItemMapper;
import com.bisheng.services.system.dao.generated.DictMapper;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.model.customized.DictItemModel;
import com.bisheng.services.system.model.generated.Dict;
import com.bisheng.services.system.model.generated.DictExample;
import com.bisheng.services.system.model.generated.DictItem;
import com.bisheng.services.system.model.generated.DictItemExample;
import com.bisheng.services.system.model.generated.DictItemExample.Criteria;
import com.bisheng.services.system.service.DictService;
import com.github.pagehelper.PageInfo;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月3日　下午3:58:58
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DictServiceImpl extends BaseService implements DictService {
	private DictDao getDictDao() {
		return sqlSession.getMapper(DictDao.class);
	}
	
	@Override
	public PageInfo<Dict> queryDictList(DictQuery query) {
        setStartPage(query);
        List<Dict> list = getDictDao().queryDictList(query);
		return new PageInfo<>(list);
	}

	@Override
	public void addDict(Dict dict) {
		DictMapper dictMapper = sqlSession.getMapper(DictMapper.class);
		dictMapper.insertSelective(dict);
	}

	@Override
	public void updateDict(Dict dict) {
		DictMapper dictMapper = sqlSession.getMapper(DictMapper.class);
		DictExample example = new DictExample();
		example.createCriteria().andDictCodeEqualTo(dict.getDictCode());
		dictMapper.updateByExampleSelective(dict, example);
	}

	@Override
	public List<DictItem> queryDictItemList(DictItemQuery query) {
		DictItemMapper dictItemMapper = sqlSession.getMapper(DictItemMapper.class);
		DictItemExample example = new DictItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictCodeEqualTo(query.getDictCode());
		if(StringUtils.isNotBlank(query.getItemCode())){
			criteria.andItemCodeEqualTo(query.getItemCode());
		}
		example.setOrderByClause(" sequence asc ");
		return dictItemMapper.selectByExample(example);
	}

	@Override
	public void addDictItem(DictItem dictItem) {
		DictItemMapper dictItemMapper = sqlSession.getMapper(DictItemMapper.class);
		dictItem.setStatus(AvailStatusEnum.AVAILABLE.getKey());
		dictItemMapper.insertSelective(dictItem);
	}

	@Override
	public void updateDictItem(DictItemModel dictItemModel) {
		DictItemMapper dictItemMapper = sqlSession.getMapper(DictItemMapper.class);
		DictItem dictItem = new DictItem();
		BeanUtils.copyProperties(dictItemModel, dictItem);
		DictItemExample example = new DictItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictCodeEqualTo(dictItemModel.getDictCode());
		criteria.andItemCodeEqualTo(dictItemModel.getOldItemCode());
		dictItemMapper.updateByExampleSelective(dictItem, example);
	}

	@Override
	public DictItem queryDictItem(String dictCode, String itemCode) {
		DictItemMapper dictItemMapper = sqlSession.getMapper(DictItemMapper.class);
		DictItemExample example = new DictItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictCodeEqualTo(dictCode);
		criteria.andItemCodeEqualTo(itemCode);
		example.setOrderByClause(" sequence asc ");
		List<DictItem> list = dictItemMapper.selectByExample(example);
		
		return list.isEmpty() ? null:list.get(0);
	}

}
