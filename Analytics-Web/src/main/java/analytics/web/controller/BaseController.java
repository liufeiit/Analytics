package analytics.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.dataobject.UserDO;
import analytics.core.service.AnalyticsService;
import analytics.core.service.AppService;
import analytics.core.service.EventService;
import analytics.core.service.LabelService;
import analytics.core.service.ModelService;
import analytics.core.service.Result;
import analytics.core.service.UserService;
import analytics.web.handler.SessionManager;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:55:47
 */
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier(value = "analyticsService")
	protected AnalyticsService analyticsService;

	@Autowired
	@Qualifier(value = "remoteAnalytics")
	protected AnalyticsService remoteAnalytics;

	@Autowired
	@Qualifier(value = "appService")
	protected AppService appService;

	@Autowired
	@Qualifier(value = "eventService")
	protected EventService eventService;

	@Autowired
	@Qualifier(value = "labelService")
	protected LabelService labelService;

	@Autowired
	@Qualifier(value = "modelService")
	protected ModelService modelService;

	@Autowired
	@Qualifier(value = "userService")
	protected UserService userService;
	
	protected ModelAndView lineDataView(ModelAndView mv, Number[][] data, String label, String tip_start, String tip_end) {
		mv.addObject("data", JSONArray.fromObject(data));
		mv.addObject("label", label);
		mv.addObject("tip_start", tip_start);
		mv.addObject("tip_end", tip_end);
		return mv;
	}
	
	protected ModelAndView returnApps(HttpServletRequest request) {
		ModelAndView mv = newViewWithUserAndApps(request, "apps", "应用", "应用概况");
		return mv;
	}

	protected ModelAndView returnView(ModelAndView mv, Result result) {
		mv.addObject("success", result.isSuccess());
		mv.addObject("message", result.getMessage());
		mv.addObject("errorCode", result.getErrorCode());
		return mv;
	}
	
	protected ModelAndView post(String action, Map<String, Object> data, String title) {
		return post(action, data, false, null, title);
	}
	
	protected ModelAndView post(String action, Map<String, Object> data, boolean alert, String alertMsg, String title) {
		ModelAndView mv = new ModelAndView("post");
		mv.addObject("data", data);
		mv.addObject("action", action);
		mv.addObject("title", title);
		if(alert) {
			mv.addObject("alert", alert);
			mv.addObject("alertMsg", alertMsg);
		}
		return mv;
	}
	
	protected ModelAndView newViewWithUserAndApps(HttpServletRequest request, String name, String nav, String nav_desc) {
		ModelAndView mv = new ModelAndView(name);
		UserDO user = getLoginUser(request);
		mv.addObject("name", user.getName());
		mv.addObject("uid", user.getId());

		mv.addObject("nav", nav);
		mv.addObject("nav_desc", nav_desc);
		
		Result result = appService.getAllApp(false);
		mv.addObject("success", result.isSuccess());
		mv.addObject("hasApp", result.isSuccess());
		mv.addObject("allApp", result.get("allApp"));
		
		return mv;
	}
	
	protected void userLogin(HttpServletRequest request, UserDO user) {
		SessionManager.login(request.getSession(true), user);
	}
	
	protected UserDO getLoginUser(HttpServletRequest request) {
		return SessionManager.getUser(request.getSession(true));
	}
	
	protected long getUserId(HttpServletRequest request) {
		UserDO user = getLoginUser(request);
		if(user == null) {
			return -1L;
		}
		return user.getId();
	}
}