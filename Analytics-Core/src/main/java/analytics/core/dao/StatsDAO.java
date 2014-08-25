package analytics.core.dao;

import analytics.core.dataobject.StatsDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:00:33
 */
public interface StatsDAO {
	void addStats(StatsDO stats) throws DAOException;
	void updateStats(StatsDO stats) throws DAOException;
	StatsDO selectStats(long statsId) throws DAOException;
	void removeStats(StatsDO stats) throws DAOException;
}