package analytics.core.service;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:12:56
 */
public interface LabelService {

	Result createLabel(long eventId, long modelId, String name, String description);
	
	Result getEventLabel(long eventId);
}