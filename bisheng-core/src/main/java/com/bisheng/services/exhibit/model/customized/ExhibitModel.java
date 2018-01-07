package com.bisheng.services.exhibit.model.customized;

import com.bisheng.services.exhibit.model.generated.Exhibit;

/**
 * 展馆模型类
 * @author lihao
 */
public class ExhibitModel extends Exhibit {
    private String parentId;// 父级ID

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
}
