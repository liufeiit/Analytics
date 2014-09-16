package analytics.web.handler;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import tulip.util.StringUtil;
import analytics.core.dataobject.UserDO;
import analytics.web.util.Static;
import analytics.web.util.Static.CharsetUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月16日 下午12:54:09
 */
public class Session {

	private static final Log log = LogFactory.getLog(Session.class);

	public static void login(HttpSession session, RedisTemplate<String, String> redisTemplate, UserDO user) {
		final String sessionId = session.getId();
		final String userGson = Static.gson.toJson(user);
		final String name = user.getName();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hSet(CharsetUtils.getUTF8Bytes(sessionId), CharsetUtils.getUTF8Bytes(Static.ONLINE_USER),
						CharsetUtils.getUTF8Bytes(userGson));
				log.error("User Session[" + sessionId + "] named : " + name + " is login Success.");
				System.err.println("User Session[" + sessionId + "] named : " + name + " is login Success.");
				return true;
			}
		});
	}

	public static void logout(HttpSession session, RedisTemplate<String, String> redisTemplate) {
		final String sessionId = session.getId();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hDel(CharsetUtils.getUTF8Bytes(sessionId), CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
				log.error("User Session[" + sessionId + "] " + " is logout Success.");
				System.err.println("User Session[" + sessionId + "] " + " is logout Success.");
				return true;
			}
		});
	}

	public static boolean isLogin(HttpSession session, RedisTemplate<String, String> redisTemplate) {
		final String sessionId = session.getId();
		Boolean isLogin = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hExists(CharsetUtils.getUTF8Bytes(sessionId), CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
			}
		});
		if(isLogin != null) {
			return isLogin;
		}
		return false;
	}

	public static UserDO getUser(HttpSession session, RedisTemplate<String, String> redisTemplate) {
		final String sessionId = session.getId();
		return redisTemplate.execute(new RedisCallback<UserDO>() {
			@Override
			public UserDO doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bytes = connection.hGet(CharsetUtils.getUTF8Bytes(sessionId), CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
				String userGson = CharsetUtils.buildFromUTF8(bytes);
				if(StringUtil.isBlank(userGson)) {
					return null;
				}
				return Static.gson.fromJson(userGson, UserDO.class);
			}
		});
	}
}