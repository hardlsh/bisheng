package com.bisheng.services.exhibit.model.customized;

import com.bisheng.services.exhibit.model.generated.BoothWord;

/**
 * 展位文字模型类
 * 
 * @author lihao
 */
public class BoothWordModel extends BoothWord {
    private Integer xCount;// x轴字数
    private Integer yCount;// y轴字数
	private Long totalCount;// 文字总数
	private Integer searchTotal;// 输入框内的文字数量
    
	public Integer getxCount() {
		return xCount;
	}
	public void setxCount(Integer xCount) {
		this.xCount = xCount;
	}
	public Integer getyCount() {
		return yCount;
	}
	public void setyCount(Integer yCount) {
		this.yCount = yCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSearchTotal() {
		return searchTotal;
	}
	public void setSearchTotal(Integer searchTotal) {
		this.searchTotal = searchTotal;
	}
	
}
