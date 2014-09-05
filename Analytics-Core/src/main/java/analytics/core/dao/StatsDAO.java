package analytics.core.dao;

import java.util.Date;

import analytics.core.dataobject.StatsDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:00:33
 */
public interface StatsDAO {
	void insertStats(StatsDO stats) throws DAOException;
	void updateStats(StatsDO stats) throws DAOException;
	StatsDO selectStats(long statsId) throws DAOException;
	void deleteStats(StatsDO stats) throws DAOException;
	
	void incrStat(long labelId, int year, int accumulation, Date date) throws DAOException;
	void incrStat(long labelId, int year, int month, int accumulation, Date date) throws DAOException;
	void incrStat(long labelId, int year, int month, int day, int accumulation, Date date) throws DAOException;
	void incrStat(long labelId, int year, int month, int day, int hour, int accumulation, Date date) throws DAOException;
	
	void deleteStats(long labelId) throws DAOException;
}