package analytics.web.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月16日 上午11:51:09
 */
public class SessionHandler implements HttpSessionListener {

	private final Log log = LogFactory.getLog(getClass());

	private RedisTemplate<String, String> redisTemplate;

	@SuppressWarnings("unchecked")
	public RedisTemplate<String, String> getRedisTemplate(ServletContext servletContext) {
		if (redisTemplate == null) {
			try {
				WebApplicationContext applicationContext = WebApplicationContextUtils
						.getRequiredWebApplicationContext(servletContext);
				redisTemplate = (RedisTemplate<String, String>) applicationContext.getBean("redisTemplate");
			} catch (Exception e) {
				log.error("getRedisTemplate Error.", e);
			}
		}
		return redisTemplate;
	}

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.error("Session : " + session.getId() + " created.");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		SessionManager.logout(session, getRedisTemplate(session.getServletContext()));
	}
}