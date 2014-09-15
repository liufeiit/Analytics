package analytics.core.util;

/**
 * 错误码
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午7:52:58
 */
public enum ErrorCode {
	Success								(200, 		"成功"),
	Error_AppID							(-100, 		"请输入合法的AppID"),
	Error_LabelID						(-200, 		"请输入合法的LabelID"),
	Error_Accumulation					(-300, 		"请输入合法的Accumulation"),
	
	Error_CreateApp						(-400, 		"创建应用失败"),
	Error_DeleteApp						(-500, 		"删除应用失败"),

	Error_CreateEvent					(-600, 		"创建应用统计事件失败"),

	Error_CreateLabel					(-700, 		"创建应用统计事件的分类失败"),

	Error_CreateModel					(-800, 		"创建应用统计事件的分类展示模型失败"),

	Error_CreateUser					(-1000, 	"创建用户失败"),
	
	Error_UserLogin						(-2000, 	"用户登录失败"),
	Error_NonUser						(-2001, 	"用户不存在"),
	Error_ErrPasswd						(-2002, 	"密码错误"),
	
	Error_Query							(-3000, 	"查询失败"),
	
	Error_Permission					(-9000, 	"非法权限"),
	
	;
	public final int code;
	public final String description;
	private ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public boolean eq(int code) {
		return this.code == code;
	}
	
	public boolean eq(ErrorCode code) {
		return this.code == code.code;
	}
	
	public static ErrorCode parse(int code) {
		for(ErrorCode e : values()) {
			if(e.code == code) {
				return e;
			}
		}
		return Error_UserLogin;
	}
	
	public static String getMessage(int code) {
		for(ErrorCode e : values()) {
			if(e.code == code) {
				return e.description;
			}
		}
		return null;
	}
}