package com.bisheng.core.framework.exception;
/**
 * 小贷后台服务业务异常基类<br />涉及业务相关异常或业务错误提示可用此类抛出异常
 * 
 */
public class BusinessException extends RuntimeException {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5737523884837538976L;

	/**
	 * 构造方法
	 */
	public BusinessException() {
	}
	
	/**
	 * 构造方法
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}
	
	/**
	 * 构造方法
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造方法
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
