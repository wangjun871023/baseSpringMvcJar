package com.macrosoft.core;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author xiao
 */
public class ServicesException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServicesException() {
		super();
	}

	public ServicesException(String message) {
		super(message);
	}

	public ServicesException(Throwable cause) {
		super(cause);
	}

	public ServicesException(String message, Throwable cause) {
		super(message, cause);
	}
}
