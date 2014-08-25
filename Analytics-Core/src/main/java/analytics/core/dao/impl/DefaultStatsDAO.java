package analytics.core.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.StatsDAO;
import analytics.core.dataobject.StatsDO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:08:21
 */
@Repository("statsDAO")
public class DefaultStatsDAO extends BaseDAO implements StatsDAO {

	public static final String ADD_SQL = "INSERT INTO stats "
			+ "(label_id, year, month, day, hour, accumulation, attr, gmt_created, gmt_modified) VALUES "
			+ "(:labelId, :year, :month, :day, :hour, :accumulation, :attr, NOW(), NOW());";

	public static final String UPDATE_SQL = "UPDATE stats SET "
			+ "label_id = :labelId, year = :year, month = :month, day = :day, hour = :hour, "
			+ "accumulation = :accumulation, attr = :attr, gmt_modified = NOW() WHERE id = :id;";

	public static final String SELECT_SQL = "SELECT id, label_id, year, month, day, hour, accumulation, attr, gmt_created, gmt_modified "
			+ "FROM stats WHERE id = :id;";

	public static final String DELETE_SQL = "DELETE FROM stats WHERE id = :id;";

	@Override
	public void addStats(StatsDO stats) throws DAOException {
		try {
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(stats));
		} catch (DataAccessException e) {
			log.error("AddStats Error.", e);
			throw new DAOException("AddStats Error.", e);
		}
	}

	@Override
	public void updateStats(StatsDO stats) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(stats));
		} catch (DataAccessException e) {
			log.error("UpdateStats Error.", e);
			throw new DAOException("UpdateStats Error.", e);
		}
	}

	@Override
	public StatsDO selectStats(long statsId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_SQL, BeanParameterMapper.newSingleParameterMapper("id", statsId),
					BeanRowMapper.newInstance(StatsDO.class));
		} catch (DataAccessException e) {
			log.error("SelectStats Error.", e);
			throw new DAOException("SelectStats Error.", e);
		}
	}

	@Override
	public void removeStats(StatsDO stats) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newInstance(stats));
		} catch (DataAccessException e) {
			log.error("RemoveStats Error.", e);
			throw new DAOException("RemoveStats Error.", e);
		}
	}
}