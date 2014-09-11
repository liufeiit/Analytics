package analytics.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.service.Result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月11日 上午11:09:50
 */
@Controller
public class Event extends BaseController {

	@RequestMapping(value = "/create_event.htm")
	public ModelAndView create_event_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_event", "创建事件", "事件概况");
		Result result = appService.getAllApp(false);
		mv.addObject("hasApp", result.isSuccess());
		mv.addObject("allApp", result.get("allApp"));
		return mv;
	}

	@RequestMapping(value = "/event_create.htm")
	public ModelAndView create_avent(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		long app_id = NumberUtils.toLong(request.getParameter("app_id"), -1L);
		if (app_id <= 0L) {
			return post("create_event.htm", data, true, "请选择所属应用", "创建中...");
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Result result = eventService.createEvent(app_id, name, description);
		if (result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_event.htm", data, true, result.getMessage(), "创建中...");
	}
}