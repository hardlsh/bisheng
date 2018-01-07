package com.bisheng.core.framework.exception;

import java.io.Serializable;

/**
 * Copyright (c) 2016, shao.liu@mljr.com All Rights Reserved.
 * date: 2016/10/19 10:56 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public enum RetCode implements Serializable {
    /**
     * 操作成功
     **/
    SUCCESS("0000", "操作成功"),

    /**
     * 操作失败
     **/
    FAILURE("9999", "操作失败"),

    /**
     * 输入参数不合法
     **/
    INVALID_ARGS("1001", "输入参数格式非法"),

    /**
     * 系统异常
     **/
    SYSTEM_ERROR("1002", "系统异常"),

    /**
     * 登录错误
     **/
    LOGIN_ERROR("1003", "未登录"),

    /**
     * 未获取到数据
     **/
    NOT_FOUND("1004", "未获取到数据"),

    /**
     * 业务数据已存在
     **/
    DATA_REPEAT("1005", "业务数据已存在"),

    /**
     * 参数为空
     **/
    DATA_EMPTY("1006", "参数为空"),

    /**
     * 请求重复
     **/
    REQUEST_REPEAT("1007", "请求重复"),

    /**
     * 表示未知错误
     **/
    UNKNOWN_ERROR("1009", "未知错误");

    private String description;

    private String code;

    RetCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 该返回码所对应的返回信息。
     *
     * @return 返回信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 该返回码所对应的数字码。该数字码只用于区分不同的返回码以及追踪版本变更。
     *
     * @return 返回的数字表示
     */
    public String getCode() {
        return code;
    }
}
