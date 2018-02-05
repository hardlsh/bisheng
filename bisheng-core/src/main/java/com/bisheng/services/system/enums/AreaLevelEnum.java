package com.bisheng.services.system.enums;

/**
 * 地区级别枚举类
 * 
 * @author shihao.li
 */
public enum AreaLevelEnum {
	PROVINCE(1, "省/直辖市"),
	CITY(2, "地级市"),
	COUNTY(3, "县级市");
	
    private final int key;
    private final String desc;

    private AreaLevelEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (AreaLevelEnum item : AreaLevelEnum.values()) {
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
