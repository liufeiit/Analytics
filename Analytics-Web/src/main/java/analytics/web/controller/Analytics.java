package analytics.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import analytics.core.dataobject.LabelDO;
import analytics.core.service.Result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午7:18:31
 */
@Controller
public class Analytics extends BaseController {

	@RequestMapping(value = "/event")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		long appId = NumberUtils.toLong(request.getParameter("app_id"), -1L);
		String token = request.getParameter("token");
		long labelId = NumberUtils.toLong(request.getParameter("label_id"), -1L);
		int accumulation = NumberUtils.toInt(request.getParameter("accumulation"), -1);
		return returnView(new ModelAndView("json"), analyticsService.event(appId, token, labelId, accumulation));
	}
	
	@RequestMapping(value = "/report_line.htm")
	public ModelAndView report_line(HttpServletRequest request) {
		long label_id = NumberUtils.toLong(request.getParameter("id"), -1L);
		Result result = labelService.getLabel(label_id);
		if(!result.isSuccess()) {
			return returnApps(request);
		}
		LabelDO label = (LabelDO) result.get("label");
		String name = label.getName();
		ModelAndView mv = newViewWithUser(request, "report.line", name + "统计", "统计概况");
		mv.addObject("years", years());
		mv.addObject("label_id", label_id);
		Number[][] data = new Number[][]{
				new Number[]{2, 1000.0},
				new Number[]{3, 200.0},
				new Number[]{4, 300.0},
				new Number[]{5, 600.0},
				new Number[]{6, 800.0},
				new Number[]{7, 500.0},
				new Number[]{8, 100.0},
				new Number[]{9, 900.0},
				new Number[]{10, 250.0},
				new Number[]{11, 1300.0},
				new Number[]{12, 600.0},
				new Number[]{13, 700.0},
				new Number[]{14, 800.0},
				new Number[]{15, 930.0},
				new Number[]{16, 561.0},
				new Number[]{17, 980.0},
				new Number[]{18, 2000.0},
				new Number[]{19, 3200.0},
				new Number[]{20, 5000.0}
		};
		return lineDataView(mv, data, name, "2014年9月", "日");
	}
	
	private List<Integer> years() {
		List<Integer> list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		list.add(year);
		for(int i = 0; i < 10; i++) {
			year -= 1;
			list.add(year);
		}
		return list;
	}
}