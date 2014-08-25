package analytics.core.dao.impl;

import org.springframework.stereotype.Repository;

import analytics.core.dao.AppDAO;
import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.AppDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:06:50
 */
@Repository("appDAO")
public class DefaultAppDAO extends BaseDAO implements AppDAO {

	@Override
	public void addApp(AppDO app) throws DAOException {

	}

	@Override
	public void updateApp(AppDO app) throws DAOException {

	}

	@Override
	public AppDO selectApp(long appId) throws DAOException {
		return null;
	}

	@Override
	public void removeApp(AppDO app) throws DAOException {

	}
}