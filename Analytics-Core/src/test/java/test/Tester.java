package test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import analytics.core.dao.LabelDAO;
import analytics.core.dataobject.LabelDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月17日 上午10:18:18
 */
public class Tester {
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:configurer/configurer-beans.xml");
		LabelDAO labelDAO = context.getBean(LabelDAO.class);
		LabelDO label = new LabelDO();
		label.setEventId(12);
		label.setModelId(1);
		label.setName("^_^");
		label.setDescription("^_^");
		Date date = new Date();
		label.setGmt_created(date);
		label.setGmt_modified(date);
		labelDAO.insertLabel(label);
	}
}
