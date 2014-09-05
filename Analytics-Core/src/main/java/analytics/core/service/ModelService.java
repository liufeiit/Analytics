package analytics.core.service;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:13:43
 */
public interface ModelService {

	Result createModel(int model, String name, String description);
	
	Result getModel(long modelId);
}