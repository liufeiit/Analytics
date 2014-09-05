package analytics.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月1日 下午6:07:31
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	private static final String URL = "url";
	private static final String ERROR_MSG = "errorMsg";
	private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mav = new ModelAndView("error");
		if (ex != null) {
			String errorMsg = ex.getClass().getName();
			String msg = ex.getLocalizedMessage();
			if (StringUtil.isNotBlank(msg)) {
				errorMsg += " : " + msg;
			}
			mav.addObject(ERROR_MSG, errorMsg);
		} else {
			mav.addObject(ERROR_MSG, "Server Error.");
		}
		StringBuffer requestURL = request.getRequestURL();
		String query = request.getQueryString();
		if (StringUtil.isNotBlank(query)) {
			requestURL.append("?").append(query);
		}
		mav.addObject(URL, requestURL);
		if (handler != null) {
			log.error(requestURL + ", handler : " + handler.getClass() + " Error.", ex);
		} else {
			log.error(requestURL + " Error.", ex);
		}
		return mav;
	}
}