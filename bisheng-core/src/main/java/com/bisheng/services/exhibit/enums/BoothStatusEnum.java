package com.bisheng.services.exhibit.enums;

/**
 * 展位状态枚举类
 * @author hp
 *
 */
public enum BoothStatusEnum {
	NORMAL(1, "正常"),
	MAINTAIN(2, "维护"),
	CLOSE(3, "关闭");

    private final int key;
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

    public int getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
