package com.bisheng.vo;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;

/**
 * 控制层返回视图层结果抽象类
 *
 * @author liupoyang
 * @since 2014-01-13
 */
public class ViewResult<T extends Object> {
    /**
     * 视图结果代码，小于0表示结果异常，大于或等于0表示结果正常
     */
    private int code;
    /**
     * 视图结果提示信息
     */
    private String msg;
    /**
     * 视图结果业务数据对象
     */
    private T data;
    /**
     * 视图结果扩展数据(键值存放)对象
     */
    private Map<String, Object> dict;

    /**
     * 自定义的json解析器
     */
    private static GsonBuilder GSON_BUILDER = new GsonBuilder();

	/**
	 * 创建一个实例
	 * 
	 * @return ViewResult
	 */
	public static <T extends Object> ViewResult<T> newInstance(T data) {
		ViewResult<T> instance = new ViewResult<T>(data);
		return instance;
	}
    /**
     * 构造方法
     */
    public ViewResult(T data) {
        this.data = data;
    }

    /**
     * 构造方法
     */
    public ViewResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法
     */
    public ViewResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 视图结果代码Get方法
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 视图结果提示信息Get方法
     *
     * @return
     */
    public String getMsg() {
        return msg;
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
     * 视图结果扩展数据对象Get方法
     *
     * @return
     */
    public Map<String, Object> getDict() {
        return dict;
    }

    /**
     * 设置结果代码<br />约定：小于0 表示业务失败；大于0 表示业务正常
     *
     * @param code 结果代码
     * @return
     */
    public ViewResult<T> code(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置结果提示信息
     *
     * @param msg 结果代码
     * @return
     */
    public ViewResult<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 设置结果扩展数据
     *
     * @param key 结果代码
     * @return
     */
    public ViewResult<T> put(String key, Object value) {
        if (dict == null) {
            dict = new HashMap<String, Object>();
        }
        dict.put(key, value);
        return this;
    }

    /**
     * 输出成json格式
     * 时间格式为yyyy-MM-dd
     *
     * @return
     */
    public String json() {
		return GSON_BUILDER
				.serializeNulls()
				.setDateFormat("yyyy-MM-dd")
				.create()
				.toJson(this);
    }

    /**
     * 输出成json格式
     * 时间格式为yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public String jsonWithDetailTime() {
        return GSON_BUILDER
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(this);
    }
}
