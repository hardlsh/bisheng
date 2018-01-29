package com.bisheng.services.system.enums;

/**
 * @ClassName: ResourceLevelEnum 
 * @author: shihao.li
 * @Explain: 资源级别
 * @date: 2017年4月12日 下午4:32:05  
 */
public enum ResourceLevelEnum {
    FIRSTMENU(1, "一级菜单"),
    SECONDMENU(2, "二级菜单"),
    BUTTON(3, "按钮");

    private int key;
    private String desc;

    private ResourceLevelEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static String getDescName(int key) {
        for (ResourceLevelEnum item : ResourceLevelEnum.values()) {
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
