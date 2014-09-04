package analytics.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import analytics.core.dao.DAOException;
import analytics.core.dataobject.AppDO;
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
	public Result createApp(String name, String description) {
		AppDO app = new AppDO();
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
			return Result.ERR.with(ErrorCode.Error_CreateApp);
		}
		return Result.SUCCESS.with(ErrorCode.Success);
	}

	@Override
	public AppDO getAppDO(long appId) {
		try {
			return appDAO.selectApp(appId);
		} catch (DAOException e) {
			log.error("getApp Error.", e);
		}
		return null;
	}

	@Override
	public Result deleteApp(long appId) {
		AppDO app = new AppDO();
		app.setId(appId);
		try {
			appDAO.deleteApp(app);
		} catch (DAOException e) {
			log.error("deleteApp Error.", e);
			return Result.ERR.with(ErrorCode.Delete_CreateApp);
		}
		return Result.SUCCESS.with(ErrorCode.Success);
	}

	@Override
	public List<AppDO> getAllApp() {
		try {
			return appDAO.selectAll();
		} catch (DAOException e) {
			log.error("getAllApp Error.", e);
		}
		return null;
	}
}