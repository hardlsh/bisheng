package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class BoothWord {
    /**
     *展位文字ID
     */
    private Long boothWordId;

    /**
     *文字
     */
    private String word;

    /**
     *模板名称
     */
    private String templetName;

    /**
     *展位ID
     */
    private Long boothId;

    /**
     *展位名称
     */
    private String boothName;

    /**
     *x坐标
     */
    private Integer xAxis;

    /**
     *y坐标
     */
    private Integer yAxis;

    /**
     *编号(即顺序号)
     */
    private Integer number;

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
     *展位文字ID
     */
    public Long getBoothWordId() {
        return boothWordId;
    }

    /**
     *展位文字ID
     */
    public void setBoothWordId(Long boothWordId) {
        this.boothWordId = boothWordId;
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
     *模板名称
     */
    public String getTempletName() {
        return templetName;
    }

    /**
     *模板名称
     */
    public void setTempletName(String templetName) {
        this.templetName = templetName == null ? null : templetName.trim();
    }

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
     *x坐标
     */
    public Integer getxAxis() {
        return xAxis;
    }

    /**
     *x坐标
     */
    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    /**
     *y坐标
     */
    public Integer getyAxis() {
        return yAxis;
    }

    /**
     *y坐标
     */
    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    /**
     *编号(即顺序号)
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *编号(即顺序号)
     */
    public void setNumber(Integer number) {
        this.number = number;
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