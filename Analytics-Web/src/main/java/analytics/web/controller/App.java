package analytics.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.service.Result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月10日 下午1:51:20
 */
@Controller
public class App extends BaseController {
	
	@RequestMapping(value = "/apps.htm")
	public ModelAndView apps(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "apps", "应用", "应用概况");
		Result result = appService.getAllApp();
		mv.addObject("success", result.isSuccess());
		mv.addObject("allApp", result.get("allApp"));
		return mv;
	}

	@RequestMapping(value = "/create_app.htm")
	public ModelAndView create_app_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_app", "创建应用", "应用概况");
		
		return mv;
	}
	
	@RequestMapping(value = "/create_event.htm")
	public ModelAndView create_event_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_event", "创建事件", "事件概况");
		
		return mv;
	}
	
	@RequestMapping(value = "/create_label.htm")
	public ModelAndView create_label_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "create_label", "创建标签", "标签概况");
		
		return mv;
	}
	
	@RequestMapping(value = "/setting.htm")
	public ModelAndView setting_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "setting", "设置", "设置概况");
		
		return mv;
	}
	
	@RequestMapping(value = "/create/app")
	public ModelAndView home(HttpServletRequest request) {
		long userId = getUserId(request);
		if(userId <= 0L) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("errorMsg", "对不起！您还没有登录！");
			return post("index.htm", data, true, "对不起！您还没有登录！", "登录中...");
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		return returnView(new ModelAndView("json"), appService.createApp(userId, name, description));
	}
}