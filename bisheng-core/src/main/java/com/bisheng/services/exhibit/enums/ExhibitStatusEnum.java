package com.bisheng.services.exhibit.enums;

/**
 * 展馆状态枚举类
 * @author hp
 *
 */
public enum ExhibitStatusEnum {
	START(1, "营业中"),
	REST(2, "休息"),
	STOPDOING(3, "停业");

    private final int key;
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
    
	public int getKey() {
		return key;
	}
	public String getDesc() {
		return desc;
	}
}
