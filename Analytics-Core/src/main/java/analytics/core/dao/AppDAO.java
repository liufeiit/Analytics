package analytics.core.dao;

import analytics.core.dataobject.AppDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:00:08
 */
public interface AppDAO {
	void insertApp(AppDO app) throws DAOException;
	void updateApp(AppDO app) throws DAOException;
	AppDO selectApp(long appId) throws DAOException;
	void deleteApp(AppDO app) throws DAOException;
}