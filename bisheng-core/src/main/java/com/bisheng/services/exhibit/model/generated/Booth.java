package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class Booth {
    /**
     *展位ID
     */
    private Long boothId;

    /**
     *展位名称
     */
    private String boothName;

    /**
     *展馆ID
     */
    private Long exhibitId;

    /**
     *展馆名称
     */
    private String exhibitName;

    /**
     *展位编号
     */
    private Integer sequence;

    /**
     *x轴字数
     */
    private Integer xCount;

    /**
     *y轴字数
     */
    private Integer yCount;

    /**
     *展位状态(1:正常,2:维护,3:关闭)
     */
    private Integer status;

    /**
     *导入标识(0:未导入, 1:已导入)
     */
    private Integer wordSign;

    /**
     *模板导入内容
     */
    private String wordContent;

    /**
     *备注
     */
    private String remark;

    /**
     *创建用户
     */
    private String createByUser;

    /**
     *修改用户
     */
    private String updateByUser;

    /**
     *创建时间
     */
    private Date gmtCreated;

    /**
     *更新时间
     */
    private Date gmtModified;

    /**
     *展位ID
     */
    public Long getBoothId() {
        return boothId;
    }

    /**
     *展位ID
     */
    public void setBoothId(Long boothId) {
        this.boothId = boothId;
    }

    /**
     *展位名称
     */
    public String getBoothName() {
        return boothName;
    }

    /**
     *展位名称
     */
    public void setBoothName(String boothName) {
        this.boothName = boothName == null ? null : boothName.trim();
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
     *展位编号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *展位编号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     *x轴字数
     */
    public Integer getxCount() {
        return xCount;
    }

    /**
     *x轴字数
     */
    public void setxCount(Integer xCount) {
        this.xCount = xCount;
    }

    /**
     *y轴字数
     */
    public Integer getyCount() {
        return yCount;
    }

    /**
     *y轴字数
     */
    public void setyCount(Integer yCount) {
        this.yCount = yCount;
    }

    /**
     *展位状态(1:正常,2:维护,3:关闭)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *展位状态(1:正常,2:维护,3:关闭)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *导入标识(0:未导入, 1:已导入)
     */
    public Integer getWordSign() {
        return wordSign;
    }

    /**
     *导入标识(0:未导入, 1:已导入)
     */
    public void setWordSign(Integer wordSign) {
        this.wordSign = wordSign;
    }

    /**
     *模板导入内容
     */
    public String getWordContent() {
        return wordContent;
    }

    /**
     *模板导入内容
     */
    public void setWordContent(String wordContent) {
        this.wordContent = wordContent == null ? null : wordContent.trim();
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
     *创建用户
     */
    public String getCreateByUser() {
        return createByUser;
    }

    /**
     *创建用户
     */
    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser == null ? null : createByUser.trim();
    }

    /**
     *修改用户
     */
    public String getUpdateByUser() {
        return updateByUser;
    }

    /**
     *修改用户
     */
    public void setUpdateByUser(String updateByUser) {
        this.updateByUser = updateByUser == null ? null : updateByUser.trim();
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