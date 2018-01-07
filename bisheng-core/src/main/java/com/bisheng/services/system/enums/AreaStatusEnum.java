package com.bisheng.services.system.enums;

import lombok.Getter;

/**
 * 地区状态枚举类
 * 
 * @author shihao.li
 */
public enum AreaStatusEnum {
	ACTIVATE(1, "激活"),
	FROZEN(2, "冻结");
	
	@Getter
    private final int key;
	@Getter
    private final String desc;

    private AreaStatusEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (AreaStatusEnum item : AreaStatusEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
