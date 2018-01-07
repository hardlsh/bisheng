package com.bisheng.services.exhibit.enums;

import lombok.Getter;

/**
 * 展馆状态枚举类
 * @author hp
 *
 */
public enum ExhibitStatusEnum {
	START(1, "开业"),
	REST(2, "休息"),
	STOPDOING(3, "停业");

    @Getter
    private final int key;
    @Getter
    private final String desc;

    private ExhibitStatusEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (ExhibitStatusEnum item : ExhibitStatusEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
