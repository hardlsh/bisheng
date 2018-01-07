package com.bisheng.services.system.model.customized;

import java.util.List;

import com.bisheng.services.system.model.generated.Role;
import com.bisheng.services.system.model.generated.User;

public class UserModel extends User {
	// 角色信息
    private Long roleId;// 角色ID
    private String roleName;// 角色名称
    private List<Role> roleList;// 角色对象   一对多
    private String roleNameStr;// 角色名称    字符串
    private String roleIdStr;// 角色id  字符串
    private List<Long> roleIdList;// 角色id
    
    //展馆信息
    private Long exhibitId;// 展馆ID
    private String exhibitName;// 展馆名称
    private String exhibitCode;// 展馆名称简拼,例如:SXWHG(绍兴文化馆)
    private String areaId;// 地区ID
    private String areaName;// 地区名称
    private String address;// 展馆地址
    private boolean exhibitSel;// 展馆是否被选中
    
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getExhibitId() {
		return exhibitId;
	}
	public void setExhibitId(Long exhibitId) {
		this.exhibitId = exhibitId;
	}
	public String getExhibitName() {
		return exhibitName;
	}
	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}
	public String getExhibitCode() {
		return exhibitCode;
	}
	public void setExhibitCode(String exhibitCode) {
		this.exhibitCode = exhibitCode;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isExhibitSel() {
		return exhibitSel;
	}
	public void setExhibitSel(boolean exhibitSel) {
		this.exhibitSel = exhibitSel;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public String getRoleNameStr() {
		return roleNameStr;
	}
	public void setRoleNameStr(String roleNameStr) {
		this.roleNameStr = roleNameStr;
	}
	public String getRoleIdStr() {
		return roleIdStr;
	}
	public void setRoleIdStr(String roleIdStr) {
		this.roleIdStr = roleIdStr;
	}
	public List<Long> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
    
}
