package analytics.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.EventDO;
import analytics.core.service.BaseService;
import analytics.core.service.EventService;
import analytics.core.service.Result;
import analytics.core.util.ErrorCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午9:45:19
 */
@Service("eventService")
public class DefaultEventService extends BaseService implements EventService {

	@Override
	public Result createEvent(long appId, String name, String description) {
		EventDO event = new EventDO();
		event.setAppId(appId);
		event.setName(name);
		event.setDescription(description);
		Date date = new Date();
		event.setGmt_created(date);
		event.setGmt_modified(date);
		try {
			eventDAO.insertEvent(event);
		} catch (DAOException e) {
			log.error("CreateEvent Error.", e);
			return Result.newError().with(ErrorCode.Error_CreateEvent);
		}
		return Result.newSuccess().with(ErrorCode.Success).with("event", event);
	}

	@Override
	public Result getAppEvent(long appId) {
		try {
			List<EventDO> appEvent =  eventDAO.getAppEvent(appId);
			if(CollectionUtil.isEmpty(appEvent)) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("appEvent", appEvent);
		} catch (DAOException e) {
			log.error("getAppEvent Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}

	@Override
	public Result getEvent(long eventId) {
		try {
			EventDO event = eventDAO.selectEvent(eventId);
			if(event == null) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("event", event);
		} catch (DAOException e) {
			log.error("getEvent Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}
}