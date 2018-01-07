package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class WordOperate {
    /**
     *操作ID
     */
    private Long operateId;

    /**
     *展馆ID
     */
    private Long exhibitId;

    /**
     *文字ID
     */
    private Long wordId;

    /**
     *操作类型 0-新建 1-入库 2-出库
     */
    private Integer type;

    /**
     *数量
     */
    private Long count;

    /**
     *操作人
     */
    private String operateUser;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *操作ID
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     *操作ID
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     *展馆ID
     */
    public Long getExhibitId() {
        return exhibitId;
    }

    /**
     *展馆ID
     */
    public void setExhibitId(Long exhibitId) {
        this.exhibitId = exhibitId;
    }

    /**
     *文字ID
     */
    public Long getWordId() {
        return wordId;
    }

    /**
     *文字ID
     */
    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    /**
     *操作类型 0-新建 1-入库 2-出库
     */
    public Integer getType() {
        return type;
    }

    /**
     *操作类型 0-新建 1-入库 2-出库
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *数量
     */
    public Long getCount() {
        return count;
    }

    /**
     *数量
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     *操作人
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     *操作人
     */
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser == null ? null : operateUser.trim();
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