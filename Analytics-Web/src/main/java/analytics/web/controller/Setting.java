package analytics.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月11日 上午11:12:25
 */
@Controller
public class Setting extends BaseController {

	@RequestMapping(value = "/setting.htm")
	public ModelAndView setting_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUser(request, "setting", "设置", "设置概况");
		return mv;
	}
}