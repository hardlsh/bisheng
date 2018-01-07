package com.bisheng.core.common.util;

/**
 * Copyright (c) 2016, shao.liu@mljr.com All Rights Reserved.
 * date: 2016/11/30 21:00 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public class StringUtil {

    /**
     * 截取字符串长度
     *
     * @param str
     * @param length
     * @return
     */
    public static String getEqualsLength(String str, int length) {
        if (str != null) {
            int aLength = str.length();
            if (aLength > length) {
                return str.substring(0, length);
            } else {
                return str;
            }
        }
        return str;
    }


}
