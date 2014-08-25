package analytics.core.service.impl;

import org.springframework.stereotype.Service;

import analytics.core.service.AnalyticsService;
import analytics.core.service.BaseService;
import analytics.core.service.Result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:12:29
 */
@Service(value = "analyticsService")
public class DefaultAnalyticsService extends BaseService implements AnalyticsService {

	@Override
	public Result event(long eventId) {
		return null;
	}

	@Override
	public Result event(long eventId, long labelId) {
		return null;
	}

	@Override
	public Result event(long eventId, int accumulation) {
		return null;
	}

	@Override
	public Result event(long eventId, long labelId, int accumulation) {
		return null;
	}

	@Override
	public Result beginEvent(long eventId) {
		return null;
	}

	@Override
	public Result endEvent(long eventId) {
		return null;
	}

	@Override
	public Result beginEvent(long eventId, long labelId) {
		return null;
	}

	@Override
	public Result endEvent(long eventId, long labelId) {
		return null;
	}
}