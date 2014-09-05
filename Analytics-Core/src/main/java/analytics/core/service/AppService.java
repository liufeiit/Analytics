package analytics.core.service;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午9:13:33
 */
public interface AppService {
	Result createApp(long userId, String name, String description);
	
	Result getAppDO(long appId);
	
	Result deleteApp(long appId);
	
	Result getAllApp();
}