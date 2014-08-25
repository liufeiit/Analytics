package analytics.core.dao.impl;

import analytics.core.dao.BaseDAO;
import analytics.core.dao.DAOException;
import analytics.core.dao.StatsDAO;
import analytics.core.dataobject.StatsDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:08:21
 */
public class DefaultStatsDAO extends BaseDAO implements StatsDAO {

	@Override
	public void addStats(StatsDO stats) throws DAOException {

	}

	@Override
	public void updateStats(StatsDO stats) throws DAOException {

	}

	@Override
	public StatsDO selectStats(long statsId) throws DAOException {
		return null;
	}

	@Override
	public void removeStats(StatsDO stats) throws DAOException {

	}
}