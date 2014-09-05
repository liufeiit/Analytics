package analytics.core.service.impl;

import java.util.Date;

import tulip.util.StringUtil;
import analytics.core.dao.DAOException;
import analytics.core.dataobject.UserDO;
import analytics.core.service.BaseService;
import analytics.core.service.Result;
import analytics.core.service.UserService;
import analytics.core.util.ErrorCode;
import analytics.core.util.PasswdUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午11:10:30
 */
public class DefaultUserService extends BaseService implements UserService {

	@Override
	public Result createUser(String name, String email, String mobile, String weixin, String password) {
		UserDO user = new UserDO();
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setWeixin(weixin);
		user.setPassword(PasswdUtil.signPwsswd(password));
		Date date = new Date();
		user.setGmt_created(date);
		user.setGmt_modified(date);
		try {
			userDAO.insertUser(user);
		} catch (DAOException e) {
			log.error("CreateUser Error.", e);
			return Result.newError().with(ErrorCode.Error_CreateUser);
		}
		return Result.newSuccess().with(ErrorCode.Success);
	}

	@Override
	public Result login(String name, String passwd) {
		try {
			if(StringUtil.isBlank(name)) {
				return Result.newError().with(ErrorCode.Error_NonUser);
			}
			if(StringUtil.isBlank(passwd)) {
				return Result.newError().with(ErrorCode.Error_ErrPasswd);
			}
			UserDO user = userDAO.selectUser(name);
			if(user == null) {
				return Result.newError().with(ErrorCode.Error_NonUser);
			}
			if(!PasswdUtil.signPwsswd(passwd).equals(user.getPassword())) {
				return Result.newError().with(ErrorCode.Error_ErrPasswd);
			}
			return Result.newSuccess().with(ErrorCode.Success).withUser(user);
		} catch (DAOException e) {
			log.error("Login Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_UserLogin);
	}
}