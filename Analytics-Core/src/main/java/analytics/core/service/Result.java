package analytics.core.service;

import java.io.Serializable;

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
	
	public Result() {
		this(false);
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}