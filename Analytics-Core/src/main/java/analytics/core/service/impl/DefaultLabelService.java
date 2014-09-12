package analytics.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.LabelDO;
import analytics.core.service.BaseService;
import analytics.core.service.LabelService;
import analytics.core.service.Result;
import analytics.core.util.ErrorCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:23:40
 */
@Service("labelService")
public class DefaultLabelService extends BaseService implements LabelService {

	@Override
	public Result getLabel(long labelId) {
		try {
			LabelDO label = labelDAO.selectLabel(labelId);
			if(label == null) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("label", label);
		} catch (DAOException e) {
			log.error("getLabel Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}

	@Override
	public Result createLabel(long eventId, long modelId, String name, String description) {
		LabelDO label = new LabelDO();
		label.setEventId(eventId);
		label.setModelId(modelId);
		label.setName(name);
		label.setDescription(description);
		Date date = new Date();
		label.setGmt_created(date);
		label.setGmt_modified(date);
		try {
			labelDAO.insertLabel(label);
		} catch (DAOException e) {
			log.error("CreateLabel Error.", e);
			return Result.newError().with(ErrorCode.Error_CreateLabel);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result getEventLabel(long eventId) {
		try {
			List<LabelDO> eventLabel = labelDAO.getEventLabel(eventId);
			if(CollectionUtil.isEmpty(eventLabel)) {
				return Result.newError().with(ErrorCode.Error_Query);
			}
			return Result.newSuccess().with(ErrorCode.Success).with("eventLabel", eventLabel);
		} catch (DAOException e) {
			log.error("getEventLabel Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_Query);
	}
}