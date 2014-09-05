package analytics.core.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import analytics.core.dataobject.UserDO;
import analytics.core.util.ErrorCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月19日 下午2:06:51
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message;
	private int errorCode;
	
	private final Map<String, Object> data = new HashMap<String, Object>();
	
	public Result() {
		this(false);
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}
	
	public static Result newError() {
		return new Result(false);
	}
	
	public static Result newSuccess() {
		return new Result(true);
	}
	
	public Result withUser(UserDO user) {
		data.put("user", user);
		return this;
	}
	
	public UserDO getUser() {
		return (UserDO) data.get("user");
	}
	
	public Result with(String key, Object value) {
		data.put(key, value);
		return this;
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