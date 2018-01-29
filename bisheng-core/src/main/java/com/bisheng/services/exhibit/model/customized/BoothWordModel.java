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
}
