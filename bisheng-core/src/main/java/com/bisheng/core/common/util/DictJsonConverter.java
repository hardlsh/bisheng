package com.bisheng.core.common.util;

import java.security.InvalidParameterException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.core.framework.redis.SpringUtil;
import com.bisheng.services.system.model.generated.DictItem;
import com.bisheng.services.system.service.DictService;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jian.wu 2016年3月2日 下午2:26:57
 */
@Slf4j
public class DictJsonConverter {
	
	private static DictService dictService;
	
	static{
		dictService = (DictService) SpringUtil.getBean("dictServiceImpl");
	}

    public static <T> String buildDictJson(String dictCode) {
    	
    	if(StringUtils.isBlank(dictCode)){
    		throw new InvalidParameterException();
    	}
    	
        try {
        	DictItemQuery query = new DictItemQuery();
        	query.setDictCode(dictCode);
        	List<DictItem> list = dictService.queryDictItemList(query);
        	
        	
            JsonObject jsonObject = new JsonObject();
            for (DictItem ec : list) {
                Object key = ec.getItemCode();
                Object value = ec.getItemName();
                if (key != null) {
                    jsonObject.addProperty(key.toString(), value.toString());
                }
            }

            return jsonObject.toString();
        } catch (Exception e) {
            log.error("构建Dict的json数据异常, dictCode is " + dictCode, e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(DictJsonConverter.buildDictJson("CAPITAL_DICT"));
    }

}
