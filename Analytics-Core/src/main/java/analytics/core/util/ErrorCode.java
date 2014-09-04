package analytics.core.util;

/**
 * 错误码
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午7:52:58
 */
public enum ErrorCode {
	Success(1, "成功"),
	Error_AppID(100, "请输入合法的AppID"),
	Error_LabelID(200, "请输入合法的LabelID"),
	Error_Accumulation(300, "请输入合法的Accumulation"),
	;
	public final int code;
	public final String description;
	private ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
}