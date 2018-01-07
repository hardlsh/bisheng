package com.bisheng.services.exhibit.enums;

import lombok.Getter;

/**
 * 展位状态枚举类
 * @author hp
 *
 */
public enum BoothStatusEnum {
	NORMAL(1, "正常"),
	MAINTAIN(2, "维护"),
	CLOSE(3, "关闭");

    @Getter
    private final int key;
    @Getter
    private final String desc;

    private BoothStatusEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (BoothStatusEnum item : BoothStatusEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
