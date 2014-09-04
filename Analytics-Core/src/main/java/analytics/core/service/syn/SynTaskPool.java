package analytics.core.service.syn;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月29日 下午3:18:29
 */
public class SynTaskPool {
	
	private static final SynTaskPool SYN_TASK_POOL = new SynTaskPool();
	
	private final Log log = LogFactory.getLog(getClass());

	private final ThreadFactory TF = new ThreadFactory() {
		public Thread newThread(Runnable runnable) {
			final UncaughtExceptionHandler dueh = Thread.getDefaultUncaughtExceptionHandler();
			Thread t = new Thread(Thread.currentThread().getThreadGroup(), runnable);
			t.setName("Analytics-SynEventTask" + System.currentTimeMillis());
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			t.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
				public void uncaughtException(Thread t, Throwable e) {
					log.error(t.getName() + " Error.", e);
					dueh.uncaughtException(t, e);
				}});
			log.error("new Thread Worker named : " + t.getName());
			return t;
		}
	};
	
	private final RejectedExecutionHandler H = new RejectedExecutionHandler() {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			try {
				r.run();
			} catch (Exception e) {
				log.error("Execute Rejected Task Error.", e);
			}
		}
	};
	
	private final ThreadPoolExecutor taskPoolExecutor = newThreadPool(1);
	
	public static void execute(Runnable task) {
		SynTaskPool.SYN_TASK_POOL.taskPoolExecutor.execute(task);
	}
	
	private ThreadPoolExecutor newThreadPool(int threads) {
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), TF, H) {
					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						super.beforeExecute(t, r);
						log.error("BeforeExecute SynTask : " + r);
					}
					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						super.afterExecute(r, t);
						log.error("AfterExecute SynTask : " + r);
						if(t != null) {
							log.error("Execute Error.", t);
						}
					}
		};
		executor.allowCoreThreadTimeOut(false);
		return executor;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			final int num = i;
			SynTaskPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("Thread say : " + num);
				}
			});
		}
	}
	
	private SynTaskPool(){}
}