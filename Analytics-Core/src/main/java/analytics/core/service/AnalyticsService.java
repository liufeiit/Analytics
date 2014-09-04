package analytics.core.service;

import org.springframework.context.ApplicationEvent;

/**
 * 事件统计服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:52:39
 */
public interface AnalyticsService {
	
	void event(long labelId);
	void event(long labelId, int accumulation);
	
	public class AnalyticsServiceEvent extends ApplicationEvent {

		private static final long serialVersionUID = 1L;

		public AnalyticsServiceEvent(Object source) {
			super(source);
		}
		
		public AnalyticsService getAnalyticsService() {
			return (AnalyticsService) getSource();
		}
	}
}