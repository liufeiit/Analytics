package analytics.core.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import analytics.core.service.AnalyticsService;
import analytics.core.service.BaseService;
import analytics.core.service.syn.SynEventTask;
import analytics.core.service.syn.SynTaskPool;
import analytics.core.service.syn.TaskCommand;
import analytics.core.util.Static;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:12:29
 */
@Service(value = "analyticsService")
public class DefaultAnalyticsService extends BaseService implements AnalyticsService {

	@Override
	public void event(long labelId) {
		SynEventTask task = new SynEventTask(new Date(), TaskCommand.Event);
		task.initialize(this);
		task.setAccumulation(Static.DEFAULT_ACCUMULATION);
		task.setLabelId(labelId);
		SynTaskPool.execute(task);
	}

	@Override
	public void event(long labelId, int accumulation) {
		SynEventTask task = new SynEventTask(new Date(), TaskCommand.Event);
		task.initialize(this);
		task.setAccumulation(accumulation);
		task.setLabelId(labelId);
		SynTaskPool.execute(task);
	}
}