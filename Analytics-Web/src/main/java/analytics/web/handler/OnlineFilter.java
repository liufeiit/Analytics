package analytics.web.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import analytics.web.util.Static;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月9日 下午5:47:15
 */
public class OnlineFilter extends GenericFilterBean {

	private final static String[] INGORE_URLS = new String[] { "login.htm", "index.htm", "analytics/event", "/image/", "/js/", "/css/", "/fonts/" };

	private RedisTemplate<String, String> redisTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		if(redisTemplate == null) {
			try {
				WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
				redisTemplate = (RedisTemplate<String, String>) applicationContext.getBean("redisTemplate");
			} catch (Exception e) {
				logger.error("getRedisTemplate Error.", e);
			}
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Object data = request.getSession().getAttribute(Static.ONLINE_USER);
		String reqURL = request.getRequestURL().toString();
		if (!(isIngore(request, reqURL)) && data == null) {
			response.sendRedirect(INGORE_URLS[1]);
			return;
		}
		if(StringUtils.contains(reqURL, "report_line.htm")) {
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader ("Expires", 0);
		}
		chain.doFilter(request, response);
	}

	private boolean isIngore(HttpServletRequest request, String reqURL) {
		for (String url : INGORE_URLS) {
			if (StringUtils.contains(reqURL, url)) {
				return true;
			}
		}
		return false;
	}
}