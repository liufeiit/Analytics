package analytics.core.dao.impl;

import org.springframework.stereotype.Repository;

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

	@Override
	public void addModel(ModelDO model) throws DAOException {
	}

	@Override
	public void updateModel(ModelDO model) throws DAOException {
	}

	@Override
	public ModelDO selectModel(long modelId) throws DAOException {
		return null;
	}

	@Override
	public void removeModel(ModelDO model) throws DAOException {
	}
}