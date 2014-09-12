package analytics.core.service;

/**
 * 事件统计服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:52:39
 */
public interface AnalyticsService {
	
	Result event(long appId, String token, long labelId, int accumulation);
	
	Result checkPermission(long appId, String token);

	Result report(long labelId, int year, int month, int day, int type);
}