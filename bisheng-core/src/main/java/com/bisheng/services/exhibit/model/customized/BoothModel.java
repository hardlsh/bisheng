package com.bisheng.services.exhibit.model.customized;

import com.bisheng.services.exhibit.model.generated.Booth;

/**
 * 展位模型类
 * 
 * @author lihao
 */
public class BoothModel extends Booth {
	/**
	 * 模板入库数量
	 */
	private Long templateCount;

	public Long getTemplateCount() {
		return templateCount;
	}

	public void setTemplateCount(Long templateCount) {
		this.templateCount = templateCount;
	}
	
}
