package analytics.core.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.LabelDAO;
import analytics.core.dataobject.LabelDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月19日 下午3:08:56
 */
@Repository("labelDAO")
public class DefaultLabelDAO extends BaseDAO implements LabelDAO {

	public static final String ADD_SQL = "INSERT INTO label "
			+ "(event_id, model_id, name, description, gmt_created, gmt_modified) VALUES "
			+ "(:event_id, :model_id, :name, :description, NOW(), NOW());";
	
	public static final String UPDATE_SQL = "UPDATE label SET "
			+ "event_id = :event_id, model_id = :model_id, name = :name, description = :description, gmt_modified = NOW() WHERE id = :id;";
	
	public static final String SELECT_SQL = "SELECT id, event_id, model_id, name, description, gmt_created, gmt_modified FROM label WHERE id = :id;";
	
	public static final String SELECT_EVENT_LABEL_SQL = "SELECT id, event_id, model_id, name, description, gmt_created, gmt_modified FROM label WHERE event_id = :event_id;";
	
	public static final String DELETE_SQL = "DELETE FROM label WHERE id = :id;";
	
	@Override
	public void insertLabel(LabelDO label) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(label), holder, new String[]{ "id" });
			Number id = holder.getKey();
			label.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("AddLabel Error.", e);
			throw new DAOException("AddLabel Error.", e);
		}
	}

	@Override
	public void updateLabel(LabelDO label) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(label));
		} catch (DataAccessException e) {
			log.error("UpdateLabel Error.", e);
			throw new DAOException("UpdateLabel Error.", e);
		}
	}

	@Override
	public LabelDO selectLabel(long labelId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_SQL, 
					BeanParameterMapper.newSingleParameterMapper("id", labelId), BeanRowMapper.newInstance(LabelDO.class));
		} catch (DataAccessException e) {
			log.error("SelectLabel Error.", e);
			throw new DAOException("SelectLabel Error.", e);
		}
	}

	@Override
	public List<LabelDO> getEventLabel(long eventId) throws DAOException {
		try {
			return jdbcTemplate.query(SELECT_EVENT_LABEL_SQL, BeanParameterMapper.newSingleParameterMapper("event_id", eventId), BeanRowMapper.newInstance(LabelDO.class));
		} catch (DataAccessException e) {
			log.error("getEventLabel Error.", e);
			throw new DAOException("getEventLabel Error.", e);
		}
	}

	@Override
	public void deleteLabel(LabelDO label) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newInstance(label));
		} catch (DataAccessException e) {
			log.error("RemoveLabel Error.", e);
			throw new DAOException("RemoveLabel Error.", e);
		}
	}
}