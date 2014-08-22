package analytics.core.service;

/**
 * 事件统计服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:52:39
 */
public interface AnalyticsService {
	Result event(long eventId);
	Result event(long eventId, long labelId);
	
	Result event(long eventId, int accumulation);
	Result event(long eventId, long labelId, int accumulation);
	
	Result beginEvent(long eventId);
	Result endEvent(long eventId);
	
	Result beginEvent(long eventId, long labelId);
	Result endEvent(long eventId, long labelId);
}