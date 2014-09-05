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
			return Result.newError().with(ErrorCode.Error_CreateModel);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result getModel(long modelId) {
		try {
			ModelDO model = modelDAO.selectModel(modelId);
			if(model == null) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("model", model);
		} catch (DAOException e) {
			log.error("getModel Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}
}