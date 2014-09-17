package analytics.web.handler;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月16日 上午11:51:09
 */
public class SessionHandler implements HttpSessionListener {

	private final Log log = LogFactory.getLog(getClass());

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.error("Session : " + session.getId() + " created.");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		SessionManager.logout(session);
	}
}