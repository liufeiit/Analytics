package analytics.core.service;

import java.util.List;

import analytics.core.dataobject.AppDO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午9:13:33
 */
public interface AppService {
	Result createApp(String name, String description);
	
	AppDO getAppDO(long appId);
	
	Result deleteApp(long appId);
	
	List<AppDO> getAllApp();
}