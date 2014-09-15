package analytics.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.AppDO;
import analytics.core.dataobject.StatsDO;
import analytics.core.service.AnalyticsService;
import analytics.core.service.BaseService;
import analytics.core.service.Result;
import analytics.core.service.syn.SynEventTask;
import analytics.core.service.syn.SynTaskPool;
import analytics.core.service.syn.TaskCommand;
import analytics.core.util.CalendarUtil;
import analytics.core.util.ErrorCode;
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
	public Result report(long labelId, int year, int month, int day, int type) {
		if(Static.YEAR == type) {
			return report_year(labelId, year, 10, type);
		} else if(Static.MONTH == type) {
			return report_month(labelId, year, month, type);
		} else if(Static.DAY_OF_MONTH == type) {
			
		} else if(Static.HOUR_OF_DAY == type) {
			
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}
	
	private Result report_month(long labelId, int year, int month, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectMonthStats(labelId, year, month);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newError().with(ErrorCode.Error_Report);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			int[] months = new int[]{};
			Number[][] data = new Number[12][2];
			/*for(int i = 0; i < count; i++) {
				int y = years.get(i);
				Double d = statsMapper.get(y);
				if(d == null || d < 0d) {
					d = 0.0d;
				}
				data[i][0] = y;
				data[i][1] = d;
			}*/
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", "").with("tip_end", "");
		} catch (DAOException e) {
			log.error("report_year Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Report);
	}

	private Result report_year(long labelId, int year, int count, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectYearStats(labelId, year, count);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newError().with(ErrorCode.Error_Report);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			List<Integer> years = CalendarUtil.years(count, CalendarUtil.INT_ASC);
			Number[][] data = new Number[count][2];
			for(int i = 0; i < count; i++) {
				int y = years.get(i);
				Double d = statsMapper.get(y);
				if(d == null || d < 0d) {
					d = 0.0d;
				}
				data[i][0] = y;
				data[i][1] = d;
			}
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", "").with("tip_end", "");
		} catch (DAOException e) {
			log.error("report_year Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Report);
	}

	private Map<Integer, Double> statsMapper(List<StatsDO> statsList, int type) {
		Map<Integer, Double> mapper = new HashMap<Integer, Double>();
		for (StatsDO stats : statsList) {
			if(Static.YEAR == type) {
				mapper.put(stats.getYear(), stats.getAccumulation());
			} else if(Static.MONTH == type) {
				mapper.put(stats.getMonth(), stats.getAccumulation());
			} else if(Static.DAY_OF_MONTH == type) {
				mapper.put(stats.getDay(), stats.getAccumulation());
			} else if(Static.HOUR_OF_DAY == type) {
				mapper.put(stats.getHour(), stats.getAccumulation());
			}
		}
		return mapper;
	}

	@Override
	public Result event(long appId, String token, long labelId, int accumulation) {
		if (labelId <= 0L) {
			return Result.newError().with(ErrorCode.Error_LabelID);
		}
		if (accumulation <= 0) {
			return Result.newError().with(ErrorCode.Error_Accumulation);
		}
		Result result = checkPermission(appId, token);
		if (!result.isSuccess()) {
			return result;
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
			if (app == null) {
				return Result.newError().with(ErrorCode.Error_Permission);
			}
			if (!app.getToken().equals(token)) {
				return Result.newError().with(ErrorCode.Error_Permission);
			}
			return Result.newSuccess().with(ErrorCode.Success);
		} catch (DAOException e) {
			log.error("checkPermission Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Permission);
	}
}