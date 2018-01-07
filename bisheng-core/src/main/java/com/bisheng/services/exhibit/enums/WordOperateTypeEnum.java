package com.bisheng.services.exhibit.enums;

import lombok.Getter;

/**
 * 文字操作类型枚举类
 * @author lihao
 *
 */
public enum WordOperateTypeEnum {
	CREATE(1, "新建"),
	IN(2, "入库"),
	OUT(3, "出库");

    @Getter
    private final int key;
    @Getter
    private final String desc;

    private WordOperateTypeEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public static String getDescName(int key) {
        for (WordOperateTypeEnum item : WordOperateTypeEnum.values()) {
            if (item.getKey() == key) {
                return item.getDesc();
            }
        }
        return "";
    }
    
}
