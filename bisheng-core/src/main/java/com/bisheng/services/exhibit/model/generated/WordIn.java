package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class WordIn {
    /**
     *入库ID
     */
    private Long inId;

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
     *入库数量
     */
    private Long inNumber;

    /**
     *入库日期
     */
    private Date inDate;

    /**
     *入库人
     */
    private String inUser;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *入库ID
     */
    public Long getInId() {
        return inId;
    }

    /**
     *入库ID
     */
    public void setInId(Long inId) {
        this.inId = inId;
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
     *入库数量
     */
    public Long getInNumber() {
        return inNumber;
    }

    /**
     *入库数量
     */
    public void setInNumber(Long inNumber) {
        this.inNumber = inNumber;
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
     *入库人
     */
    public String getInUser() {
        return inUser;
    }

    /**
     *入库人
     */
    public void setInUser(String inUser) {
        this.inUser = inUser == null ? null : inUser.trim();
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