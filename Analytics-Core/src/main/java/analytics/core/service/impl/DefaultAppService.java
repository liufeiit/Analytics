package analytics.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.AppDO;
import analytics.core.dataobject.EventDO;
import analytics.core.dataobject.LabelDO;
import analytics.core.service.AppService;
import analytics.core.service.BaseService;
import analytics.core.service.Result;
import analytics.core.util.ErrorCode;
import analytics.core.util.TokenUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午9:14:58
 */
@Service("appService")
public class DefaultAppService extends BaseService implements AppService {

	@Override
	public Result createApp(long userId, String name, String description) {
		AppDO app = new AppDO();
		app.setUserId(userId);
		app.setName(name);
		app.setDescription(description);
		app.setToken(TokenUtils.generate());
		Date date = new Date();
		app.setGmt_created(date);
		app.setGmt_modified(date);
		try {
			appDAO.insertApp(app);
		} catch (DAOException e) {
			log.error("createApp Error.", e);
			return Result.newError().with(ErrorCode.Error_CreateApp);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result getAppDO(long appId) {
		try {
			AppDO app = appDAO.selectApp(appId);
			if(app == null) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("app", app);
		} catch (DAOException e) {
			log.error("getApp Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}

	@Override
	public Result deleteApp(long appId) {
		AppDO app = new AppDO();
		app.setId(appId);
		try {
			appDAO.deleteApp(app);
			List<EventDO> appEvent = eventDAO.getAppEvent(appId);
			if(CollectionUtil.isEmpty(appEvent)) {
				return Result.newSuccess().with(ErrorCode.Success);
			}
			for (EventDO eventDO : appEvent) {
				eventDAO.deleteEvent(eventDO);
				List<LabelDO> eventLabel = labelDAO.getEventLabel(eventDO.getId());
				if(CollectionUtil.isEmpty(eventLabel)) {
					continue;
				}
				for (LabelDO labelDO : eventLabel) {
					labelDAO.deleteLabel(labelDO);
					statsDAO.deleteStats(labelDO.getId());
				}
			}
		} catch (DAOException e) {
			log.error("deleteApp Error.", e);
			return Result.newError().with(ErrorCode.Error_DeleteApp);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result getAllApp() {
		try {
			List<AppDO> allApp =  appDAO.selectAll();
			if(CollectionUtil.isEmpty(allApp)) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("allApp", allApp);
		} catch (DAOException e) {
			log.error("getAllApp Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}
}