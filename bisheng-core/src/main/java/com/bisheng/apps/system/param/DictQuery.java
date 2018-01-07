package com.bisheng.apps.system.param;

import com.bisheng.apps.BaseQuery;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月2日　下午5:53:28
 * @version 1.0
 * @since JDK 1.8
 */
@SuppressWarnings("serial")
public class DictQuery extends BaseQuery {

    /**
     *字典编号
     */
    private String dictCode;

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
    
}
