/**
 * @(#)SettleOrderStatus.java, 2016年1月29日. Copyright (c) 2015, Youjie8 and/or
 * its affiliates. All rights reserved. Youjie8 PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */
package com.bisheng.services.system.enums;

import lombok.Getter;

/**
 * @author shihao.li
 * 用户状态  激活状态才能使用
 */
public enum UserStatusEnum {
	CREATE(0, "新建"),
    ACTIVE(1, "激活"),
    FROZEN(2, "冻结"),
    STOP(3, "停用");
	
	@Getter
    private final int key;
	@Getter
    private final String desc;

    private UserStatusEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
