package analytics.core.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.UserDAO;
import analytics.core.dataobject.UserDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:59:30
 */
@Repository("userDAO")
public class DefaultUserDAO extends BaseDAO implements UserDAO {

	public static final String ADD_SQL = "INSERT INTO usr "
			+ "(name, email, mobile, weixin, password, gmt_created, gmt_modified) VALUES "
			+ "(:name, :email, :mobile, :weixin, :password, NOW(), NOW());";

	public static final String SELECT_BYNAME_SQL = "SELECT id, name, email, mobile, weixin, password, gmt_created, gmt_modified FROM usr WHERE name = :name;";
	
	@Override
	public void insertUser(UserDO user) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(user), holder, new String[]{ "id" });
			Number id = holder.getKey();
			user.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("InsertUser Error.", e);
			throw new DAOException("InsertUser Error.", e);
		}
	}

	@Override
	public UserDO selectUser(String name) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_BYNAME_SQL, 
					BeanParameterMapper.newSingleParameterMapper("name", name), BeanRowMapper.newInstance(UserDO.class));
		} catch (DataAccessException e) {
			log.error("SelectUser Error.", e);
			throw new DAOException("SelectUser Error.", e);
		}
	}
}