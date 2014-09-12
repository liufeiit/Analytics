package analytics.core.dao.impl;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import tulip.data.jdbc.mapper.ClassMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.StatsDAO;
import analytics.core.dao.statement.StatsMapper;
import analytics.core.dataobject.StatsDO;
import analytics.core.util.Static;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:08:21
 */
@Repository("statsDAO")
public class DefaultStatsDAO extends BaseDAO implements StatsDAO, StatsMapper {
	
	String SELECT_YEAR_STATS_SQL = "SELECT year, sum(accumulation) AS accumulation FROM stats "
			+ "WHERE label_id = :labelId AND type = 1 AND year <= :year GROUP BY year LIMIT :count;";
	
	String SELECT_MONTH_STATS_SQL = "SELECT month, sum(accumulation) AS accumulation FROM stats "
			+ "WHERE label_id = :labelId AND type = 2 AND year = :year AND month <= :month GROUP BY month;";
	
	String SELECT_DAY_STATS_SQL = "SELECT day, sum(accumulation) AS accumulation FROM stats "
			+ "WHERE label_id = :labelId AND type = 3 AND year = :year AND month = :month AND day <= :day GROUP BY day;";
	
	String SELECT_HOUR_STATS_SQL = "SELECT hour, sum(accumulation) AS accumulation FROM stats "
			+ "WHERE label_id = :labelId AND type = 4 AND year = :year AND month = :month AND day = :day AND hour <= :hour GROUP BY hour;";
	
	@Override
	public List<StatsDO> selectYearStats(long labelId, int year, int count) throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("year", year);
			paramMap.put("count", count);
			paramMap.put("labelId", labelId);
			return jdbcTemplate.query(SELECT_YEAR_STATS_SQL, BeanParameterMapper.newMapParameterMapper(paramMap), 
					BeanRowMapper.newInstance(StatsDO.class));
		} catch (DataAccessException e) {
			log.error("SelectYearStats Error.", e);
			throw new DAOException("SelectYearStats Error.", e);
		}
	}
	
	@Override
	public List<StatsDO> selectMonthStats(long labelId, int year, int month) throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("labelId", labelId);
			return jdbcTemplate.query(SELECT_MONTH_STATS_SQL, BeanParameterMapper.newMapParameterMapper(paramMap), 
					BeanRowMapper.newInstance(StatsDO.class));
		} catch (DataAccessException e) {
			log.error("SelectMonthStats Error.", e);
			throw new DAOException("SelectMonthStats Error.", e);
		}
	}
	
	@Override
	public List<StatsDO> selectDayStats(long labelId, int year, int month, int day) throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("day", day);
			paramMap.put("labelId", labelId);
			return jdbcTemplate.query(SELECT_DAY_STATS_SQL, BeanParameterMapper.newMapParameterMapper(paramMap), 
					BeanRowMapper.newInstance(StatsDO.class));
		} catch (DataAccessException e) {
			log.error("SelectDayStats Error.", e);
			throw new DAOException("SelectDayStats Error.", e);
		}
	}
	
	@Override
	public List<StatsDO> selectHourStats(long labelId, int year, int month, int day, int hour) throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("day", day);
			paramMap.put("hour", hour);
			paramMap.put("labelId", labelId);
			return jdbcTemplate.query(SELECT_HOUR_STATS_SQL, BeanParameterMapper.newMapParameterMapper(paramMap), 
					BeanRowMapper.newInstance(StatsDO.class));
		} catch (DataAccessException e) {
			log.error("SelectDayStats Error.", e);
			throw new DAOException("SelectDayStats Error.", e);
		}
	}
	
	@Override
	public void incrStat(long labelId, int year, int accumulation, Date date) throws DAOException {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("labelId", labelId);
		paramMap.put("year", year);
		paramMap.put("type", Static.YEAR);
		paramMap.put("accumulation", accumulation);
		paramMap.put("gmt_modified", date);
		Long count = jdbcTemplate.queryForObject(CHECK_STAT_YEAR, paramMap, Long.class);
		if(count == null || count <= 0) {
			StatsDO stats = new StatsDO();
			stats.setAccumulation(accumulation);
			stats.setAttr(0);
			stats.setDay(0);
			stats.setGmt_created(date);
			stats.setGmt_modified(date);
			stats.setHour(0);
			stats.setLabelId(labelId);
			stats.setMonth(0);
			stats.setType(Static.YEAR);
			stats.setYear(year);
			insertStats(stats);
			return;
		}
		try {
			jdbcTemplate.update(INCR_STAT_YEAR, paramMap);
		} catch (DataAccessException e) {
			log.error("UpdateStats Error.", e);
			throw new DAOException("UpdateStats Error.", e);
		}
	}
	
	@Override
	public void incrStat(long labelId, int year, int month, int accumulation, Date date) throws DAOException {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("labelId", labelId);
		paramMap.put("year", year);
		paramMap.put("month", month);
		paramMap.put("type", Static.MONTH);
		paramMap.put("accumulation", accumulation);
		paramMap.put("gmt_modified", date);
		Long count = jdbcTemplate.queryForObject(CHECK_STAT_MONTH, paramMap, Long.class);
		if(count == null || count <= 0) {
			StatsDO stats = new StatsDO();
			stats.setAccumulation(accumulation);
			stats.setAttr(0);
			stats.setDay(0);
			stats.setGmt_created(date);
			stats.setGmt_modified(date);
			stats.setHour(0);
			stats.setLabelId(labelId);
			stats.setMonth(month);
			stats.setType(Static.MONTH);
			stats.setYear(year);
			insertStats(stats);
			return;
		}
		try {
			jdbcTemplate.update(INCR_STAT_MONTH, paramMap);
		} catch (DataAccessException e) {
			log.error("UpdateStats Error.", e);
			throw new DAOException("UpdateStats Error.", e);
		}
	}
	
	@Override
	public void incrStat(long labelId, int year, int month, int day, int accumulation, Date date) throws DAOException {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("labelId", labelId);
		paramMap.put("year", year);
		paramMap.put("month", month);
		paramMap.put("day", day);
		paramMap.put("type", Static.DAY_OF_MONTH);
		paramMap.put("accumulation", accumulation);
		paramMap.put("gmt_modified", date);
		Long count = jdbcTemplate.queryForObject(CHECK_STAT_DAY, paramMap, Long.class);
		if(count == null || count <= 0) {
			StatsDO stats = new StatsDO();
			stats.setAccumulation(accumulation);
			stats.setAttr(0);
			stats.setDay(day);
			stats.setGmt_created(date);
			stats.setGmt_modified(date);
			stats.setHour(0);
			stats.setLabelId(labelId);
			stats.setMonth(month);
			stats.setType(Static.DAY_OF_MONTH);
			stats.setYear(year);
			insertStats(stats);
			return;
		}
		try {
			jdbcTemplate.update(INCR_STAT_DAY, paramMap);
		} catch (DataAccessException e) {
			log.error("UpdateStats Error.", e);
			throw new DAOException("UpdateStats Error.", e);
		}
	}
	
	@Override
	public void incrStat(long labelId, int year, int month, int day, int hour, int accumulation, Date date) throws DAOException {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("labelId", labelId);
		paramMap.put("year", year);
		paramMap.put("month", month);
		paramMap.put("day", day);
		paramMap.put("hour", hour);
		paramMap.put("type", Static.HOUR_OF_DAY);
		paramMap.put("accumulation", accumulation);
		paramMap.put("gmt_modified", date);
		Long count = jdbcTemplate.queryForObject(CHECK_STAT_HOUR, paramMap, Long.class);
		if(count == null || count <= 0) {
			StatsDO stats = new StatsDO();
			stats.setAccumulation(accumulation);
			stats.setAttr(0);
			stats.setDay(day);
			stats.setGmt_created(date);
			stats.setGmt_modified(date);
			stats.setHour(hour);
			stats.setLabelId(labelId);
			stats.setMonth(month);
			stats.setType(Static.HOUR_OF_DAY);
			stats.setYear(year);
			insertStats(stats);
			return;
		}
		try {
			jdbcTemplate.update(INCR_STAT_HOUR, paramMap);
		} catch (DataAccessException e) {
			log.error("UpdateStats Error.", e);
			throw new DAOException("UpdateStats Error.", e);
		}
	}
	
	@Override
	public void insertStats(StatsDO stats) throws DAOException {
		try {
			Date date = new Date();
			if(stats.getGmt_created() == null) {
				stats.setGmt_created(date);
			}
			if(stats.getGmt_modified() == null) {
				stats.setGmt_modified(date);
			}
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(stats));
		} catch (DataAccessException e) {
			log.error("AddStats Error.", e);
			throw new DAOException("AddStats Error.", e);
		}
	}
	
	public static void main(String[] args) {
		Map<String, PropertyDescriptor> np = ClassMapper.mapper(StatsDO.class);
		for(Map.Entry<String, PropertyDescriptor> e : np.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue().getName());
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
	public void deleteStats(StatsDO stats) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newInstance(stats));
		} catch (DataAccessException e) {
			log.error("RemoveStats Error.", e);
			throw new DAOException("RemoveStats Error.", e);
		}
	}

	@Override
	public void deleteStats(long labelId) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_LABEL_SQL, BeanParameterMapper.newSingleParameterMapper("label_id", labelId));
		} catch (DataAccessException e) {
			log.error("RemoveStats Error.", e);
			throw new DAOException("RemoveStats Error.", e);
		}
	}
}