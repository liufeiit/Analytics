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
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月10日 下午1:51:20
 */
@Controller
public class App extends BaseController {
	
	@RequestMapping(value = "/apps.htm")
	public ModelAndView apps(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "apps", "应用", "应用概况");
		Result result = appService.getAllApp(false);
		mv.addObject("success", result.isSuccess());
		mv.addObject("allApp", result.get("allApp"));
		return mv;
	}
	
	@RequestMapping(value = "/create_label.htm")
	public ModelAndView create_label_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_label", "创建标签", "标签概况");
		Result result = appService.getAllApp(true);
		mv.addObject("hasApp", result.isSuccess());
		mv.addObject("allApp", result.get("allApp"));
		return mv;
	}
	
	@RequestMapping(value = "/label_create.htm")
	public ModelAndView create_label(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		long event_id = NumberUtils.toLong(request.getParameter("event_id"), -1L);
		if(event_id <= 0L) {
			return post("create_label.htm", data, true, "请选择所属事件", "创建中...");
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Result result = labelService.createLabel(event_id, 0, name, description);
		if(result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_label.htm", data, true, result.getMessage(), "创建中...");
	}
	
	@RequestMapping(value = "/setting.htm")
	public ModelAndView setting_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "setting", "设置", "设置概况");
		return mv;
	}

	@RequestMapping(value = "/create_app.htm")
	public ModelAndView create_app_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_app", "创建应用", "应用概况");
		return mv;
	}
	
	@RequestMapping(value = "/app_create.htm")
	public ModelAndView create_app(HttpServletRequest request) {
		long userId = getUserId(request);
		if(userId <= 0L) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("errorMsg", "对不起！您还没有登录！");
			return post("index.htm", data, true, "对不起！您还没有登录！", "登录中...");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Result result = appService.createApp(userId, name, description);
		if(result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_app.htm", data, true, result.getMessage(), "创建中...");
	}
	
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
		if(app_id <= 0L) {
			return post("create_event.htm", data, true, "请选择所属应用", "创建中...");
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Result result = eventService.createEvent(app_id, name, description);
		if(result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_event.htm", data, true, result.getMessage(), "创建中...");
	}
}