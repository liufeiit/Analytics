package analytics.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import analytics.core.service.AnalyticsService;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:55:47
 */
public class BaseController {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier(value="analyticsService")
	protected AnalyticsService analyticsService;
}