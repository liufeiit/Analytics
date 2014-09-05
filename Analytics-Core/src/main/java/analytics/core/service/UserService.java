package analytics.core.service;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午11:08:30
 */
public interface UserService {

	Result createUser(String name, String email, String mobile, String weixin, String password);
	
	Result login(String name, String passwd);
}