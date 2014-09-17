package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import analytics.core.context.Application;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月17日 上午10:18:18
 */
public class Tester {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:configurer/configurer-beans.xml");
		System.out.println(Application.isRedisAvailable());
	}
}
