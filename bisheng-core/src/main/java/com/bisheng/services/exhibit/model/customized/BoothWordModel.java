package com.bisheng.services.exhibit.model.customized;

import com.bisheng.services.exhibit.model.generated.BoothWord;

/**
 * 展位文字模型类
 * 
 * @author lihao
 */
public class BoothWordModel extends BoothWord {
    private Long exhibitId;// 展馆ID
    private String exhibitName;// 展馆名称
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
	public Long getExhibitId() {
		return exhibitId;
	}
	public void setExhibitId(Long exhibitId) {
		this.exhibitId = exhibitId;
	}
	public String getExhibitName() {
		return exhibitName;
	}
	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}
	
}
