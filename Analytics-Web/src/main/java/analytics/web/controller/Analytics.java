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
		mv.addObject(
				
				"data", 
				
				"[ "
				+ "[ 2, 300.0 ], [ 3, 93.3 ], [ 4, 102.0 ], "
				+ "[ 5, 108.5 ], [ 6, 115.7 ], [ 7, 215.6 ], "
				+ "[ 8, 194.6 ], [ 9, 230.3 ], [ 10, 164.3 ], "
				+ "[ 11, 241.4 ], [ 12, 146.5 ], [ 13, 151.7 ], "
				+ "[ 14, 159.9 ], [ 15, 162.4 ], [ 16, 267.8 ], "
				+ "[ 17, 268.7 ], [ 18, 129.5 ], [ 19, 268.0 ] "
				+ "]"
				
				);
		return mv;
	}
}