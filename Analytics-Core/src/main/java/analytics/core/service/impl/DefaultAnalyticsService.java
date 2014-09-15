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
			return report_day(labelId, year, month, day, type);
		} else if(Static.HOUR_OF_DAY == type) {
			return report_hour(labelId, year, month, day, CalendarUtil.hour(), type);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}
	
	private Result report_hour(long labelId, int year, int month, int day, int hour, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectHourStats(labelId, year, month, day, hour);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newSuccess().with(ErrorCode.Success);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			Number[][] data = new Number[hour][2];
			for(int h = 1; h <= hour; h++) {
				Double sd = statsMapper.get(h);
				if(sd == null) {
					sd = 0.0d;
				}
				data[h][0] = h;
				data[h][1] = sd;
			}
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", year + "年" + month + "月" + day + "日").with("tip_end", "时");
		} catch (DAOException e) {
			log.error("report_month Error.", e);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}
	
	private Result report_day(long labelId, int year, int month, int day, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectDayStats(labelId, year, month, day);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newSuccess().with(ErrorCode.Success);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			Number[][] data = new Number[day][2];
			for(int d = 1; d <= day; d++) {
				Double sd = statsMapper.get(d);
				if(sd == null) {
					sd = 0.0d;
				}
				data[d][0] = d;
				data[d][1] = sd;
			}
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", year + "年" + month + "月").with("tip_end", "日");
		} catch (DAOException e) {
			log.error("report_month Error.", e);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}
	
	private Result report_month(long labelId, int year, int month, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectMonthStats(labelId, year, month);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newSuccess().with(ErrorCode.Success);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			Number[][] data = new Number[month][2];
			for(int m = 1; m <= month; m++) {
				Double sd = statsMapper.get(m);
				if(sd == null) {
					sd = 0.0d;
				}
				data[m][0] = m;
				data[m][1] = sd;
			}
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", year + "年").with("tip_end", "月");
		} catch (DAOException e) {
			log.error("report_month Error.", e);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	private Result report_year(long labelId, int year, int count, int type) {
		try {
			List<StatsDO> statsList = statsDAO.selectYearStats(labelId, year, count);
			if (CollectionUtil.isEmpty(statsList)) {
				return Result.newSuccess().with(ErrorCode.Success);
			}
			Map<Integer, Double> statsMapper = statsMapper(statsList, type);
			List<Integer> years = CalendarUtil.years(count, CalendarUtil.INT_ASC);
			Number[][] data = new Number[count][2];
			for(int i = 0; i < count; i++) {
				int y = years.get(i);
				Double sd = statsMapper.get(y);
				if(sd == null) {
					sd = 0.0d;
				}
				data[i][0] = y;
				data[i][1] = sd;
			}
			return Result.newSuccess().with(ErrorCode.Success).with("data", data).with("tip_start", "").with("tip_end", "年");
		} catch (DAOException e) {
			log.error("report_year Error.", e);
		}
		return Result.newSuccess().with(ErrorCode.Success);
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