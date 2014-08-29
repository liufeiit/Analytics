package analytics.core.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.EventDAO;
import analytics.core.dataobject.EventDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月19日 下午3:04:15
 */
@Repository("eventDAO")
public class DefaultEventDAO extends BaseDAO implements EventDAO {

	public static final String ADD_SQL = "INSERT INTO event "
			+ "(app_id, name, description, gmt_created, gmt_modified) VALUES "
			+ "(:app_id, :name, :description, NOW(), NOW());";
	
	public static final String UPDATE_SQL = "UPDATE event SET "
			+ "app_id = :app_id, name = :name, description = :description, gmt_modified = NOW() WHERE id = :id;";
	
	public static final String SELECT_SQL = "SELECT id, app_id, name, description, gmt_created, gmt_modified FROM event WHERE id = :id;";
	
	public static final String DELETE_SQL = "DELETE FROM event WHERE id = :id;";
	
	@Override
	public void insertEvent(EventDO event) throws DAOException {
		try {
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(event));
		} catch (DataAccessException e) {
			log.error("AddEvent Error.", e);
			throw new DAOException("AddEvent Error.", e);
		}
	}

	@Override
	public void updateEvent(EventDO event) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(event));
		} catch (DataAccessException e) {
			log.error("UpdateEvent Error.", e);
			throw new DAOException("UpdateEvent Error.", e);
		}
	}

	@Override
	public EventDO selectEvent(long eventId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_SQL, 
					BeanParameterMapper.newSingleParameterMapper("id", eventId), BeanRowMapper.newInstance(EventDO.class));
		} catch (DataAccessException e) {
			log.error("SelectEvent Error.", e);
			throw new DAOException("SelectEvent Error.", e);
		}
	}

	@Override
	public void deleteEvent(EventDO event) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(event));
		} catch (DataAccessException e) {
			log.error("RemoveEvent Error.", e);
			throw new DAOException("RemoveEvent Error.", e);
		}
	}
}
