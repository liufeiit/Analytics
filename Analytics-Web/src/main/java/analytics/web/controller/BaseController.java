package analytics.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import analytics.web.util.Static;

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
	
	protected ModelAndView newViewWithUser(HttpServletRequest request, String name) {
		ModelAndView mv = new ModelAndView(name);
		UserDO user = getUser(request);
		mv.addObject("name", user.getName());
		mv.addObject("uid", user.getId());
		return mv;
	}
	
	protected void setUser(HttpServletRequest request, UserDO user) {
		request.getSession(true).setAttribute(Static.ONLINE_USER, user);
	}
	
	protected UserDO getUser(HttpServletRequest request) {
		return (UserDO) request.getSession(true).getAttribute(Static.ONLINE_USER);
	}
}