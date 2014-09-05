package analytics.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

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
			return Result.ERR.with(ErrorCode.Error_CreateEvent);
		}
		return Result.SUCCESS.with(ErrorCode.Success);
	}

	@Override
	public List<EventDO> getAppEvent(long appId) {
		try {
			return eventDAO.getAppEvent(appId);
		} catch (DAOException e) {
			log.error("getAppEvent Error.", e);
		}
		return null;
	}
}