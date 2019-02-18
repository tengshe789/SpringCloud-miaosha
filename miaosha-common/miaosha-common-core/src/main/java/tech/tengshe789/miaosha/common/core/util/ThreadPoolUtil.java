package tech.tengshe789.miaosha.common.core.util;

import lombok.Getter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: -miaosha
 * @description: ThreadPool
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 16:43
 **/
public class ThreadPoolUtil {

	/**
	 * 线程缓冲队列
	 */
	private static BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(100);
	/**
	 * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
	 */
	private static final int SIZE_CORE_POOL = 5;
	/**
	 * 线程池维护线程的最大数量
	 */
	private static final int SIZE_MAX_POOL = 10;
	/**
	 * 线程池维护线程所允许的空闲时间
	 */
	private static final long ALIVE_TIME = 2000;

	@Getter
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MILLISECONDS, bqueue, new ThreadPoolExecutor.CallerRunsPolicy());

	static {
		pool.prestartAllCoreThreads();
	}

}
