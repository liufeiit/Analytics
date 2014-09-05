package analytics.core.dao;

import analytics.core.dataobject.UserDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:56:59
 */
public interface UserDAO {

	void insertUser(UserDO userDO) throws DAOException;
	
	UserDO selectUser(String name) throws DAOException;
}