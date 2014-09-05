package analytics.core.dao;

import java.util.List;

import analytics.core.dataobject.EventDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:51:29
 */
public interface EventDAO {
	void insertEvent(EventDO event) throws DAOException;
	void updateEvent(EventDO event) throws DAOException;
	EventDO selectEvent(long eventId) throws DAOException;
	void deleteEvent(EventDO event) throws DAOException;
	List<EventDO> getAppEvent(long appId) throws DAOException;
}