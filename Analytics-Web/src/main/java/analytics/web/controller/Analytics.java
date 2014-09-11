package analytics.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午7:18:31
 */
@Controller
public class Analytics extends BaseController {

	@RequestMapping(value = "/event")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		long appId = NumberUtils.toLong(request.getParameter("app_id"), -1L);
		String token = request.getParameter("token");
		long labelId = NumberUtils.toLong(request.getParameter("label_id"), -1L);
		int accumulation = NumberUtils.toInt(request.getParameter("accumulation"), -1);
		return returnView(new ModelAndView("json"), analyticsService.event(appId, token, labelId, accumulation));
	}
	
	@RequestMapping(value = "/flot.htm")
	public ModelAndView apps(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "flot", "统计", "统计概况");
		return mv;
	}
}