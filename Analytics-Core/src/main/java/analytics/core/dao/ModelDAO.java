package analytics.core.dao;

import analytics.core.dataobject.ModelDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午12:00:57
 */
public interface ModelDAO {
	void insertModel(ModelDO model) throws DAOException;
	void updateModel(ModelDO model) throws DAOException;
	ModelDO selectModel(long modelId) throws DAOException;
	void deleteModel(ModelDO model) throws DAOException;
}