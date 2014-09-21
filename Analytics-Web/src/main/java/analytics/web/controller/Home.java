package analytics.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.service.Result;
import analytics.web.handler.SessionManager;

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
	
	@RequestMapping(value = "/logout.htm")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		SessionManager.logout(request.getSession(true));
		return mv;
	}
	
	@RequestMapping(value = "/invalidate.htm")
	public ModelAndView session_invalidate(HttpServletRequest request) {
		SessionManager.logout(request.getSession(true));
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMsg", "用户登录会话失效");
		return mv;
	}
	
	@RequestMapping(value = "/login.htm")
	public ModelAndView login(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		Result result = userService.login(name, passwd);
		if(result.isSuccess()) {
			userLogin(request, result.getUser());
			return post("home.htm", data, "登录中...");
		}
		data.put("errorMsg", result.getMessage());
		return post("index.htm", data, true, result.getMessage(), "登录中...");
	}
	
	@RequestMapping(value = "/home.htm")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "home", "首页", "首页概况", false);
		return mv;
	}
	
	@RequestMapping(value = "/selected_app.htm")
	public ModelAndView selected_app(HttpServletRequest request) {
		long appId = NumberUtils.toLong(request.getParameter("selectedApp"), 0L);
		SessionManager.selectedApp(request.getSession(true), appId);
		return returnApps(request, false);
	}
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://127.0.0.1:8080/analytics/create/app?name=nova_android&description=nova_android");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			String params = "{\"label_id\":\"200\",\"accumulation\"=\"200\"}";
			byte[] b = params.toString().getBytes();// 和get的方法一样的
			httpConn.getOutputStream().write(b, 0, b.length);
			httpConn.getOutputStream().flush();
			httpConn.getOutputStream().close();
			InputStreamReader l_urlStream = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
			BufferedReader l_reader = new BufferedReader(l_urlStream);
			String sCurrentLine = "";
			String responseStr = "";
			while ((sCurrentLine = l_reader.readLine()) != null) {
				responseStr += sCurrentLine;
			}
			System.out.println(responseStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}