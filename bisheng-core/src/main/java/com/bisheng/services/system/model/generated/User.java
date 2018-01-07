package com.bisheng.services.system.model.generated;

import java.util.Date;

public class User {
    /**
     *用户ID
     */
    private Long userId;

    /**
     *登陆名称
     */
    private String loginName;

    /**
     *用户名
     */
    private String userName;

    /**
     *密码
     */
    private String password;

    /**
     *手机号
     */
    private String phone;

    /**
     *邮箱
     */
    private String email;

    /**
     *用户状态（0新建, 1激活，2冻结, 3 停用）
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
     *登陆名称
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     *登陆名称
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     *用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     *用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *密码
     */
    public String getPassword() {
        return password;
    }

    /**
     *密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     *手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     *手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     *邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     *用户状态（0新建, 1激活，2冻结, 3 停用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *用户状态（0新建, 1激活，2冻结, 3 停用）
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