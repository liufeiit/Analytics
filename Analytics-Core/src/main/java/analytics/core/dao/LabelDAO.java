package analytics.core.dao;

import analytics.core.dataobject.LabelDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:51:57
 */
public interface LabelDAO {
	void addLabel(LabelDO Label) throws DAOException;
	void updateLabel(LabelDO Label) throws DAOException;
	LabelDO selectLabel(long LabelId) throws DAOException;
	void removeLabel(LabelDO Label) throws DAOException;
}