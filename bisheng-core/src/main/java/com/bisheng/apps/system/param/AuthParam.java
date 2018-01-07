package com.bisheng.apps.system.param;

import java.util.List;

import com.bisheng.apps.BaseQuery;

/** 
 * @ClassName: AuthParam 
 * @author: shihao.li
 * @Explain: 权限模块  查询参数类
 * @date: 2017年3月27日 下午6:50:27  
 */
public class AuthParam extends BaseQuery{

	private static final long serialVersionUID = 1L;
	/**
     * 用户id
     */
	private Long userId;
    /**
     * 登陆名称
     */
    private String loginName;
    /**
     * 用户名
     */
    private String userName;
    /**
     *密码
     */
    private String password;
    /**
     * 登陆手机号
     */
    private String phone;
    /**
     * 登陆邮箱
     */
    private String email;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 用户状态（0新建, 1激活，2冻结, 3 停用）
     */
    private Integer userStatus;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色id
     */
    private List<Long> roleIdList;
    /**
     * 角色名称
     */
    private String roleName;
	/**
	 * 角色状态(0 不可用, 1 可用)
	 */
	private Integer roleStatus;
	/**
     *资源类型(0 按钮, 1 菜单)
     */
    private Integer resourceType;
    /**
     *资源状态 0-不可用 1-可用
     */
    private Integer resourceStatus;
    /**
     * 展馆状态
     */
    private Integer exhibitStatus;
    /**
     * 展馆id
     */
    private Long[] exhibitIdArr;
    
	public Long[] getExhibitIdArr() {
		return exhibitIdArr;
	}
	public void setExhibitIdArr(Long[] exhibitIdArr) {
		this.exhibitIdArr = exhibitIdArr;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<Long> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}
	public Integer getResourceType() {
		return resourceType;
	}
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	public Integer getResourceStatus() {
		return resourceStatus;
	}
	public void setResourceStatus(Integer resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	public Integer getExhibitStatus() {
		return exhibitStatus;
	}
	public void setExhibitStatus(Integer exhibitStatus) {
		this.exhibitStatus = exhibitStatus;
	}
	
}
