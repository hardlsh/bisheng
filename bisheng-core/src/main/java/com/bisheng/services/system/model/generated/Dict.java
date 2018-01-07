package com.bisheng.services.system.model.generated;

import java.util.Date;

public class Dict {
    /**
     *主键ID
     */
    private Long dictId;

    /**
     *字典编号
     */
    private String dictCode;

    /**
     *字典名称
     */
    private String dictName;

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
     *主键ID
     */
    public Long getDictId() {
        return dictId;
    }

    /**
     *主键ID
     */
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    /**
     *字典编号
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     *字典编号
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     *字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     *字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
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