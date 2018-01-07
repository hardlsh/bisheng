package com.bisheng.vo;

/**
 * Copyright (c) 2016, shao.liu@mljr.com All Rights Reserved.
 * date: 2016/10/19 10:54 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public class ALMResponse {

    protected String resultCode;

    protected String resultMsg;

    protected Object data;

    /**
     * Creates a new instance of BaseResponse.
     *
     * @param resultCode
     * @param resultMsg
     */
    public ALMResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ALMResponse(RetCode code) {
        this.resultCode = code.getCode();
        this.resultMsg = code.getDescription();
    }

    public ALMResponse(RetCode code, String resultMsg) {
        this.resultCode = code.getCode();
        this.resultMsg = resultMsg;
    }

    public ALMResponse() {
    }

    public void setResult(RetCode code) {
        this.resultCode = code.getCode();
        this.resultMsg = code.getDescription();
    }

    public void setResult(RetCode code, String resultMsg) {
        this.resultCode = code.getCode();
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
