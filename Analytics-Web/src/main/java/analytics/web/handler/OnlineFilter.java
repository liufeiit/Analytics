package analytics.web.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import analytics.web.util.Static;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月9日 下午5:47:15
 */
public class OnlineFilter implements Filter {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String reqURL = request.getRequestURL().toString();
		HttpServletResponse response = (HttpServletResponse) resp;
		Object data = request.getSession().getAttribute(Static.ONLINE_USER);
		if(!(reqURL.contains("login.htm") || reqURL.contains("index.htm") || reqURL.contains("analytics/event")) && data == null) {
			response.sendRedirect("index.htm");
			log.error("用户session失效，重新登录！");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}