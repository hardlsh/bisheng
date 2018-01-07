package com.bisheng.services.exhibit.enums;

import lombok.Getter;

/**
 * 展位导入标识枚举类
 * 
 * @author lihao
 */
public enum BoothWordSignEnum {
	NOIMPORT(0, "未导入"),
	ALIMPORT(1, "已导入");

    @Getter
    private final int key;
    @Getter
    private final String desc;

    private BoothWordSignEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (BoothWordSignEnum item : BoothWordSignEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
