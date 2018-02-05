/*
 * Copyright 2016 mljr.com All right reserved. This software is the
 * confidential and proprietary information of mljr.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with mljr.com .
 */
package com.bisheng.core.common.util;

import java.security.InvalidParameterException;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jian.wu 2016年3月2日 下午2:26:57
 */
@Slf4j
public class EnumJsonConverter {
    private static final Logger logger = LoggerFactory.getLogger(EnumJsonConverter.class);

    public static <T> String buildEnumJson(Class<T> clazz) {
        if (!clazz.isEnum()) {
            throw new InvalidParameterException();
        }

        try {
            T[] enumConstants = clazz.getEnumConstants();
            JsonObject jsonObject = new JsonObject();
            for (T ec : enumConstants) {
                Object key = PropertyUtils.getProperty(ec, "key");
                Object value = PropertyUtils.getProperty(ec, "desc");
                if (key != null) {
                    jsonObject.addProperty(key.toString(), value.toString());
                }
            }

            return jsonObject.toString();
        } catch (Exception e) {
            logger.error("构建Enum的json数据异常, clazz is " + clazz.getName(), e);
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(EnumJsonConverter.buildEnumJson(ChannelEnum.class));
    }

}
