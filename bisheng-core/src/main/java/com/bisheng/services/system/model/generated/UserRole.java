package com.bisheng.services.system.model.generated;

import java.util.Date;

public class UserRole {
    /**
     *主键ID
     */
    private Long id;

    /**
     *用户ID
     */
    private Long userId;

    /**
     *角色ID
     */
    private Long roleId;

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
    public Long getId() {
        return id;
    }

    /**
     *主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     *角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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