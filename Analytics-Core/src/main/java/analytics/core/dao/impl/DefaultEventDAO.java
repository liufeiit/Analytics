package analytics.core.dao.impl;

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

	@Override
	public void addEvent(EventDO event) throws DAOException {
		jdbcTemplate.update("", BeanParameterMapper.newInstance(event));
	}

	@Override
	public void updateEvent(EventDO event) throws DAOException {
		jdbcTemplate.update("", BeanParameterMapper.newInstance(event));
	}

	@Override
	public EventDO selectEvent(long eventId) throws DAOException {
		return jdbcTemplate.query("", BeanRowMapper.newSingleExtractor(EventDO.class));
	}

	@Override
	public void removeEvent(EventDO event) throws DAOException {

	}
}
