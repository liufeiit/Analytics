package analytics.core.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import analytics.core.dao.DAOException;
import analytics.core.dataobject.ModelDO;
import analytics.core.service.BaseService;
import analytics.core.service.ModelService;
import analytics.core.service.Result;
import analytics.core.util.ErrorCode;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:16:29
 */
@Service("modelService")
public class DefaultModelService extends BaseService implements ModelService {

	@Override
	public Result createModel(int model, String name, String description) {
		ModelDO m = new ModelDO();
		m.setModel(model);
		m.setName(name);
		m.setDescription(description);
		Date date = new Date();
		m.setGmt_created(date);
		m.setGmt_modified(date);
		try {
			modelDAO.insertModel(m);
		} catch (DAOException e) {
			log.error("CreateModel Error.", e);
			return Result.ERR.with(ErrorCode.Error_CreateModel);
		}
		return Result.SUCCESS.with(ErrorCode.Success);
	}

	@Override
	public ModelDO getModel(long modelId) {
		try {
			return modelDAO.selectModel(modelId);
		} catch (DAOException e) {
			log.error("getModel Error.", e);
		}
		return null;
	}
}