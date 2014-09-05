package analytics.core.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import analytics.core.dao.DAOException;
import analytics.core.dataobject.AppDO;
import analytics.core.service.AnalyticsService;
import analytics.core.service.BaseService;
import analytics.core.service.Result;
import analytics.core.service.syn.SynEventTask;
import analytics.core.service.syn.SynTaskPool;
import analytics.core.service.syn.TaskCommand;
import analytics.core.util.ErrorCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:12:29
 */
@Service(value = "analyticsService")
public class DefaultAnalyticsService extends BaseService implements AnalyticsService {

	@Override
	public Result event(long labelId, int accumulation) {
		if(labelId <= 0L) {
			return Result.newError().with(ErrorCode.Error_LabelID);
		}
		if(accumulation <= 0) {
			return Result.newError().with(ErrorCode.Error_Accumulation);
		}
		SynEventTask task = new SynEventTask(new Date(), TaskCommand.Event);
		task.initialize(this);
		task.setAccumulation(accumulation);
		task.setLabelId(labelId);
		SynTaskPool.execute(task);
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result checkPermission(long appId, String token) {
		try {
			AppDO app = appDAO.selectApp(appId);
			if(app == null) {
				return Result.newError().with(ErrorCode.Error_Permission);
			}
			if(!app.getToken().equals(token)) {
				return Result.newError().with(ErrorCode.Error_Permission);
			}
			return Result.newSuccess().with(ErrorCode.Success);
		} catch (DAOException e) {
			log.error("checkPermission Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Permission);
	}
}