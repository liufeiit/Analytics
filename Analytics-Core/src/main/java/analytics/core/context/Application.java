package analytics.core.context;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月17日 上午10:24:12
 */
public class Application implements ApplicationContextAware {
	private static final Log log = LogFactory.getLog(Application.class);
	
	public final static String REDIS_AVAILABLE_RESPONSE = "PONG";

	public static ApplicationContext context;
	public static RedisTemplate<String, String> redisTemplate;
	private static JedisConnectionFactory jedisConnectionFactory;
	
	private static AtomicBoolean redis_available = new AtomicBoolean(false);
	
	@Override
	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		try {
			init(applicationContext);
		} catch (Exception e) {
			log.error("Application Init Error.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void init(ApplicationContext applicationContext) throws Exception {
		Application.context = applicationContext;
		Application.redisTemplate = (RedisTemplate<String, String>) applicationContext.getBean("redisTemplate");
		Application.jedisConnectionFactory = (JedisConnectionFactory) applicationContext.getBean("jedisConnectionFactory");
		checkRedisAvailable();
		new Timer(false).schedule(new TimerTask() {
			public void run() {
				checkRedisAvailable();
			}
		}, 3000, 10000);
	}
	
	protected void checkRedisAvailable() {
		try {
			Application.redis_available.compareAndSet(false, Application.REDIS_AVAILABLE_RESPONSE.equals(Application.jedisConnectionFactory.getConnection().ping()));
			if(Application.redis_available.get()) {
				log.error(getRedisInfo() + " is available.");
				System.err.println(getRedisInfo() + " is available.");
			} else {
				log.error(getRedisInfo() + " is unavailable.");
				System.err.println(getRedisInfo() + " is unavailable.");
			}
		} catch (Exception e) {
			log.error("Check Redis Available Error.", e);
			Application.redis_available.set(false);
		}
	}
	
	public static boolean isRedisAvailable() {
		return Application.redis_available.get();
	}
	
	private String getRedisInfo() {
		return "Redis[" + Application.jedisConnectionFactory.getHostName() + ":" + Application.jedisConnectionFactory.getPort() + "]";
	}
}