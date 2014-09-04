package analytics.core.service;

import java.io.Serializable;

import analytics.core.util.ErrorCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月19日 下午2:06:51
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Result SUCCESS = Result.newInstance(true);
	public static final Result ERR = Result.newInstance(false);
	
	private boolean success;
	private String message;
	private int errorCode;
	
	public Result() {
		this(false);
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}
	
	public static Result newInstance(boolean success) {
		return new Result(success);
	}
	
	public Result with(ErrorCode errorCode) {
		errorCode(errorCode.code);
		message(errorCode.description);
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Result success(boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result message(String message) {
		this.message = message;
		return this;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Result errorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}
}