package com.bisheng.services.system.model.customized;

import java.util.List;

import com.bisheng.services.system.model.generated.Role;

public class RoleModel extends Role {
	/**
     * 资源id 字符串
     */
	private String resourceIdStr;
    /**
     * 资源id 
     */
	private List<Long> resourceIdList;
	
	public String getResourceIdStr() {
		return resourceIdStr;
	}
	public void setResourceIdStr(String resourceIdStr) {
		this.resourceIdStr = resourceIdStr;
	}
	public List<Long> getResourceIdList() {
		return resourceIdList;
	}
	public void setResourceIdList(List<Long> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}
}
