package com.bisheng.core.framework.pager;

import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 控制层返回分页结果抽象类
 *
 * @author lin.xiao
 * @since 2016-01-16
 */
public class PaginationResult<T extends Object> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 分页总数
     */
    private int iTotalRecords;

    /**
     * 过滤后总数
     */
    private int iTotalDisplayRecords;

    /**
     * 视图结果业务数据对象
     */
    private T data;

    /**
     * 自定义的json解析器
     */
    private static GsonBuilder GSON_BUILDER = new GsonBuilder();
    private String notice;

    /**
     * 创建一个实例
     *
     * @return ViewResult
     */
    public static <T extends Object> PaginationResult<T> newInstance(T data) {
        PaginationResult<T> instance = new PaginationResult<T>(data);
        return instance;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * 构造方法
     */
    public PaginationResult(T data) {
        this.data = data;
    }

    /**
     * 构造方法
     */
    public PaginationResult(int iTotalRecords, int iTotalDisplayRecords) {
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    /**
     * 构造方法
     */
    public PaginationResult(int iTotalRecords, int iTotalDisplayRecords,
                            T data) {
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.data = data;
    }

    /**
     * 视图结果业务数据对象Get方法
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * @param iTotalRecords
     * @return
     */
    public PaginationResult<T> ITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
        return this;
    }

    /**
     * @param iTotalDisplayRecords 结果代码
     * @return
     */
    public PaginationResult<T> ITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        return this;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    /**
     * 输出成json格式
     *
     * @return
     */
    public String json() {
        return GSON_BUILDER.serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(this);
    }

    public String decimalJson() {
        return GSON_BUILDER.serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(BigDecimal.class, new JsonSerializer<BigDecimal>() {
                    @Override
                    public JsonElement serialize(BigDecimal bigDecimal, Type type, JsonSerializationContext jsonSerializationContext) {
                        // NumberFormat numberFormat = NumberFormat.getNumberInstance();
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                        return new JsonPrimitive(currencyFormat.format(bigDecimal));
                    }
                }).create().toJson(this);
    }

}
