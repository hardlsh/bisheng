package com.bisheng.services.system.model.customized;

import com.bisheng.services.system.model.generated.DictItem;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: MLJR</p>
 * 
 * @author bingfeng.xie
 * @date 2016年10月20日　下午10:00:44
 * @version 1.0
 * @since JDK 1.8
 */
public class DictItemModel extends DictItem {

	/**
     * 旧字典明细编号，用于修改字典明细编号
     */
    private String oldItemCode;

	public String getOldItemCode() {
		return oldItemCode;
	}

	public void setOldItemCode(String oldItemCode) {
		this.oldItemCode = oldItemCode;
	}

}
