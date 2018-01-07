package com.bisheng.services.system.enums;

import lombok.Getter;

public enum ResourceTypeEnum {
    MENU(1, "菜单"),
    BUTTON(0, "按钮");

    @Getter
    private int key;
    @Getter
    private String desc;

    private ResourceTypeEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static String getDescName(int key) {
        for (ResourceTypeEnum item : ResourceTypeEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
}
