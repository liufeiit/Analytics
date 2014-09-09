package analytics.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.dataobject.UserDO;
import analytics.core.service.Result;

/**
 * Web首页。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午10:26:04
 */
@Controller
public class Home extends BaseController {
	
	@RequestMapping(value = "/index.htm")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMsg", request.getParameter("errorMsg"));
		return mv;
	}
	
	@RequestMapping(value = "/login.htm")
	public ModelAndView login(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String name = request.getParameter("name");
		data.put("name", name);
		String passwd = request.getParameter("passwd");
		Result result = userService.login(name, passwd);
		if(result.isSuccess()) {
			UserDO user = result.getUser();
			setUser(request.getSession(true), user);
			data.put("uid", user.getId());
			return post("home.htm", data, "登录中...");
		}
		data.put("errorMsg", result.getMessage());
		return post("index.htm", data, true, result.getMessage(), "登录中...");
	}
	
	@RequestMapping(value = "/home.htm")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("name", request.getParameter("name"));
		return mv;
	}
}