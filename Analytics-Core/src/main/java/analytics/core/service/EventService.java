package analytics.core.service;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午9:38:33
 */
public interface EventService {

	Result createEvent(long appId, String name, String description);
	
	Result getAppEvent(long appId);
}