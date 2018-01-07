package com.bisheng.apps.system.param;

/**
 * 地区查询参数
 * @author lihao
 */
public class AreaParam {
    private String areaId;// 地区ID(即身份证前6位)
    private String areaName;// 地区名称(例如:上海市)
    private String parentId;// 父级ID
    private String shortName;// 简称(例如:上海市--上海)
    private Integer level;// 级别 (1:省/直辖市, 2:地级市, 3:县级市)
    private String pinyin;// 拼音 (例如: 上海--Shanghai)
    private String jianpin;// 简拼 (例如: 上海--SH)
    private String firstChar;// 首字母 (例如: 上海--S)
    private Integer status;// 状态 (1:激活, 2:冻结)
    
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getJianpin() {
		return jianpin;
	}
	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}
	public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}
