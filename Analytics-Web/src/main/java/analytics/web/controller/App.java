package analytics.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.dataobject.AppDO;
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
		return returnApps(request);
	}
	
	@RequestMapping(value = "/stats.htm")
	public ModelAndView apps_stats(HttpServletRequest request) {
		Result result = appService.getAllApp(true);
		if(!result.isSuccess()) {
			return returnApps(request);
		}
		ModelAndView mv = newViewWithUser(request, "stats", "统计", "详情");
		mv.addObject("success", result.isSuccess());
		mv.addObject("apps", result.get("allApp"));
		return mv;
	}
	
	@RequestMapping(value = "/app_detail.htm")
	public ModelAndView app_detail_page(HttpServletRequest request) {
		long app_id = NumberUtils.toLong(request.getParameter("id"), -1L);
		Result resultApp = appService.getAppDO(app_id);
		if(!resultApp.isSuccess()) {
			return returnApps(request);
		}
		AppDO app = (AppDO) resultApp.get("app");
		String name = app.getName();
		ModelAndView mv = newViewWithUser(request, "app_events", "应用详情", name);
		mv.addObject("app_name", name);
		Result result = eventService.getAppEvent(app_id);
		mv.addObject("hasEvents", result.isSuccess());
		mv.addObject("allEvents", result.get("appEvent"));
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
		if (userId <= 0L) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("errorMsg", "对不起！您还没有登录！");
			return post("index.htm", data, true, "对不起！您还没有登录！", "登录中...");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Result result = appService.createApp(userId, name, description);
		if (result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_app.htm", data, true, result.getMessage(), "创建中...");
	}
}