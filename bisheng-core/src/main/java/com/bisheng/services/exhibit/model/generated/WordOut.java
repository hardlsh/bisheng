package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class WordOut {
    /**
     *出库ID
     */
    private Long outId;

    /**
     *展馆ID
     */
    private Long exhibitId;

    /**
     *展馆名称
     */
    private String exhibitName;

    /**
     *文字ID
     */
    private Long wordId;

    /**
     *文字
     */
    private String word;

    /**
     *出库数量
     */
    private Long outNumber;

    /**
     *出库日期
     */
    private Date outDate;

    /**
     *出库人
     */
    private String outUser;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *出库ID
     */
    public Long getOutId() {
        return outId;
    }

    /**
     *出库ID
     */
    public void setOutId(Long outId) {
        this.outId = outId;
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
     *展馆名称
     */
    public String getExhibitName() {
        return exhibitName;
    }

    /**
     *展馆名称
     */
    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName == null ? null : exhibitName.trim();
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
     *出库数量
     */
    public Long getOutNumber() {
        return outNumber;
    }

    /**
     *出库数量
     */
    public void setOutNumber(Long outNumber) {
        this.outNumber = outNumber;
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
     *出库人
     */
    public String getOutUser() {
        return outUser;
    }

    /**
     *出库人
     */
    public void setOutUser(String outUser) {
        this.outUser = outUser == null ? null : outUser.trim();
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