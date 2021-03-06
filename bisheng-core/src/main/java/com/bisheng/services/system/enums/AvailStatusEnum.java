package com.bisheng.services.system.enums;

/**
 * @author shihao.li
 */
public enum AvailStatusEnum {
	UNAVAILABLE(0, "不可用"),
	AVAILABLE(1, "可用");

    private int key;
    private String desc;

    private AvailStatusEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (AvailStatusEnum item : AvailStatusEnum.values()) {
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
