package com.bisheng.services.system.model.generated;

import java.util.Date;

public class Area {
    /**
     *地区ID(即身份证前6位)
     */
    private String areaId;

    /**
     *地区名称(例如:上海市)
     */
    private String areaName;

    /**
     *父级ID
     */
    private String parentId;

    /**
     *简称(例如:上海市--上海)
     */
    private String shortName;

    /**
     *级别 (1:省/直辖市, 2:地级市, 3:县级市)
     */
    private Integer level;

    /**
     *拼音 (例如: 上海--Shanghai)
     */
    private String pinyin;

    /**
     *简拼 (例如: 上海--SH)
     */
    private String jianpin;

    /**
     *首字母 (例如: 上海--S)
     */
    private String firstChar;

    /**
     *状态 (1:激活, 2:冻结)
     */
    private Integer status;

    /**
     *备注
     */
    private String remark;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *地区ID(即身份证前6位)
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     *地区ID(即身份证前6位)
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     *地区名称(例如:上海市)
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     *地区名称(例如:上海市)
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     *父级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     *父级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     *简称(例如:上海市--上海)
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *简称(例如:上海市--上海)
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    /**
     *级别 (1:省/直辖市, 2:地级市, 3:县级市)
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *级别 (1:省/直辖市, 2:地级市, 3:县级市)
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     *拼音 (例如: 上海--Shanghai)
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     *拼音 (例如: 上海--Shanghai)
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     *简拼 (例如: 上海--SH)
     */
    public String getJianpin() {
        return jianpin;
    }

    /**
     *简拼 (例如: 上海--SH)
     */
    public void setJianpin(String jianpin) {
        this.jianpin = jianpin == null ? null : jianpin.trim();
    }

    /**
     *首字母 (例如: 上海--S)
     */
    public String getFirstChar() {
        return firstChar;
    }

    /**
     *首字母 (例如: 上海--S)
     */
    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
    }

    /**
     *状态 (1:激活, 2:冻结)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态 (1:激活, 2:冻结)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     *备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     *创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     *更新时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     *更新时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}