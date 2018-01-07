package com.bisheng.services.system.model.generated;

import java.util.Date;

public class Resource {
    /**
     *资源ID
     */
    private Long resourceId;

    /**
     *资源名称
     */
    private String resourceName;

    /**
     *资源类型(0 按钮, 1 菜单)
     */
    private Integer resourceType;

    /**
     *父级ID
     */
    private Long parentId;

    /**
     *资源地址
     */
    private String resourcePath;

    /**
     *级别
     */
    private Integer level;

    /**
     *顺序
     */
    private Integer sequence;

    /**
     *状态 0-不可用 1-可用
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
     *资源ID
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     *资源ID
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     *资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     *资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     *资源类型(0 按钮, 1 菜单)
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     *资源类型(0 按钮, 1 菜单)
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     *父级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     *父级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     *资源地址
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     *资源地址
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    /**
     *级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *级别
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     *状态 0-不可用 1-可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态 0-不可用 1-可用
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