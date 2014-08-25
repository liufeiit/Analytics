package analytics.core.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.ModelDAO;
import analytics.core.dataobject.ModelDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:10:53
 */
@Repository("modelDAO")
public class DefaultModelDAO extends BaseDAO implements ModelDAO {
	
	public static final String ADD_SQL = "INSERT INTO model "
			+ "(model, name, description, gmt_created, gmt_modified) VALUES "
			+ "(:model, :name, :description, NOW(), NOW());";
	
	public static final String UPDATE_SQL = "UPDATE model SET "
			+ "model = :model, name = :name, description = :description, gmt_modified = NOW() WHERE id = :id;";
	
	public static final String SELECT_SQL = "SELECT id, model, name, description, gmt_created, gmt_modified FROM model WHERE id = :id;";
	
	public static final String DELETE_SQL = "DELETE FROM model WHERE id = :id;";
	
	@Override
	public void addModel(ModelDO model) throws DAOException {
		try {
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(model));
		} catch (DataAccessException e) {
			log.error("AddModel Error.", e);
			throw new DAOException("AddModel Error.", e);
		}
	}

	@Override
	public void updateModel(ModelDO model) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(model));
		} catch (DataAccessException e) {
			log.error("UpdateModel Error.", e);
			throw new DAOException("UpdateModel Error.", e);
		}
	}

	@Override
	public ModelDO selectModel(long modelId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_SQL, 
					BeanParameterMapper.newSingleParameterMapper("id", modelId), BeanRowMapper.newInstance(ModelDO.class));
		} catch (DataAccessException e) {
			log.error("SelectModel Error.", e);
			throw new DAOException("SelectModel Error.", e);
		}
	}

	@Override
	public void removeModel(ModelDO model) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newInstance(model));
		} catch (DataAccessException e) {
			log.error("RemoveModel Error.", e);
			throw new DAOException("RemoveModel Error.", e);
		}
	}
}