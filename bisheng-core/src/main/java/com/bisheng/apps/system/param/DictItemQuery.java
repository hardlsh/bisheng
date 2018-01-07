package com.bisheng.apps.system.param;

import com.bisheng.apps.BaseQuery;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月20日　下午7:12:22
 * @version 1.0
 * @since JDK 1.8
 */
@SuppressWarnings("serial")
public class DictItemQuery extends BaseQuery {

    /**
     *字典编号
     */
    private String dictCode;

    /**
     *字典明细编号
     */
    private String itemCode;

    /**
     *字典明细名称
     */
    private String itemName;

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
}
