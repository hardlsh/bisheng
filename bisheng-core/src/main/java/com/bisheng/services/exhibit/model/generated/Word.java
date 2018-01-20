package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class Word {
    /**
     *文字ID
     */
    private Long wordId;

    /**
     *展馆ID
     */
    private Long exhibitId;

    /**
     *文字
     */
    private String word;

    /**
     *入库日期
     */
    private Date inDate;

    /**
     *出库日期
     */
    private Date outDate;

    /**
     *文字总数
     */
    private Long totalCount;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

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
     *文字
     */
    public String getWord() {
        return word;
    }

    /**
     *文字
     */
    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    /**
     *入库日期
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     *入库日期
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    /**
     *出库日期
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     *出库日期
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     *文字总数
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     *文字总数
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
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