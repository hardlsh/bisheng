package com.bisheng.services.exhibit.model.generated;

import java.util.Date;

public class Exhibit {
    /**
     *展馆ID
     */
    private Long exhibitId;

    /**
     *展馆名称
     */
    private String exhibitName;

    /**
     *展馆名称简拼,例如:SXWHG(绍兴文化馆)
     */
    private String exhibitCode;

    /**
     *地区ID
     */
    private String areaId;

    /**
     *地区名称
     */
    private String areaName;

    /**
     *展馆地址
     */
    private String address;

    /**
     *联系方式
     */
    private String phone;

    /**
     *展馆开放状态(1:开业,2:休息,3:停业)
     */
    private Integer status;

    /**
     *展览开始时间
     */
    private String startTime;

    /**
     *展览结束时间
     */
    private String endTime;

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
     *展馆名称简拼,例如:SXWHG(绍兴文化馆)
     */
    public String getExhibitCode() {
        return exhibitCode;
    }

    /**
     *展馆名称简拼,例如:SXWHG(绍兴文化馆)
     */
    public void setExhibitCode(String exhibitCode) {
        this.exhibitCode = exhibitCode == null ? null : exhibitCode.trim();
    }

    /**
     *地区ID
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     *地区ID
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     *地区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     *地区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     *展馆地址
     */
    public String getAddress() {
        return address;
    }

    /**
     *展馆地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     *联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     *联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *展馆开放状态(1:开业,2:休息,3:停业)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *展馆开放状态(1:开业,2:休息,3:停业)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *展览开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     *展览开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     *展览结束时间
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     *展览结束时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
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