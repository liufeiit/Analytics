package analytics.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月16日 下午3:15:51
 */
@Controller
public class Configurer extends BaseController {

	@RequestMapping(value = "/configurer.htm")
	public ModelAndView configurer_page(HttpServletRequest request) {
		ModelAndView mv = newViewWithUserAndApps(request, "configurer", "在线参数", "参数概况");
		return mv;
	}
}