package com.bisheng.services.system.model.generated;

import java.util.Date;

public class DictItem {
    /**
     *字典明细ID
     */
    private Long itemId;

    /**
     *字典编号
     */
    private String dictCode;

    /**
     *字典明细编号
     */
    private String itemCode;

    /**
     *字典明细名称
     */
    private String itemName;

    /**
     *关键字
     */
    private String keyWord;

    /**
     *状态(0 不可用, 1 可用)
     */
    private Integer status;

    /**
     *顺序
     */
    private Integer sequence;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *字典明细ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     *字典明细ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
     *字典明细编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     *字典明细编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     *字典明细名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *字典明细名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     *关键字
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     *关键字
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    /**
     *状态(0 不可用, 1 可用)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态(0 不可用, 1 可用)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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