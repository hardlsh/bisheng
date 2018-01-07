package com.bisheng.services.exhibit.model.customized;

import java.util.List;

import com.bisheng.services.exhibit.model.generated.Word;

/**
 * 文字存量模型类
 * @author lihao
 */
public class WordModel extends Word {
	 /**
     *展馆名称
     */
    private String exhibitName;
    /**
     * 文字id集合
     */
    private List<Long> wordIdList;
    /**
     * 操作类型
     */
    private Integer type;
    /**
     * 操作数量汇总
     */
    private Long operateCount;
    
	public String getExhibitName() {
		return exhibitName;
	}

	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}

	public List<Long> getWordIdList() {
		return wordIdList;
	}

	public void setWordIdList(List<Long> wordIdList) {
		this.wordIdList = wordIdList;
	}

	public Long getOperateCount() {
		return operateCount;
	}

	public void setOperateCount(Long operateCount) {
		this.operateCount = operateCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
}
