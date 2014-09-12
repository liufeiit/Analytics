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
 * @since 2014年9月11日 上午11:10:36
 */
@Controller
public class Label extends BaseController {
	
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
		if (event_id <= 0L) {
			return post("create_label.htm", data, true, "请选择所属事件", "创建中...");
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		long model_id = NumberUtils.toLong(request.getParameter("model_id"), 1l);
		Result result = labelService.createLabel(event_id, model_id, name, description);
		if (result.isSuccess()) {
			return post("apps.htm", data, "创建中...");
		}
		return post("create_label.htm", data, true, result.getMessage(), "创建中...");
	}
}