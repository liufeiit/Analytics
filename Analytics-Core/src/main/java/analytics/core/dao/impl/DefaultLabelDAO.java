package analytics.core.dao.impl;

import org.springframework.stereotype.Repository;

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

	@Override
	public void addLabel(LabelDO Label) throws DAOException {
		
	}

	@Override
	public void updateLabel(LabelDO Label) throws DAOException {

	}

	@Override
	public LabelDO selectLabel(long LabelId) throws DAOException {
		return null;
	}

	@Override
	public void removeLabel(LabelDO Label) throws DAOException {

	}
}