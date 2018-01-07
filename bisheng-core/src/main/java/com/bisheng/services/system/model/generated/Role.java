package com.bisheng.services.system.model.generated;

import java.util.Date;

public class Role {
    /**
     *角色ID
     */
    private Long roleId;

    /**
     *角色名称
     */
    private String roleName;

    /**
     *状态(0 不可用, 1 可用)
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
     *角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     *角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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