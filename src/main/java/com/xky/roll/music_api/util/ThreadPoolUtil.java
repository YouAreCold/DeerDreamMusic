package com.xky.roll.music_api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 线程池的创建
 * @author yangshugan
 *
 */
public class ThreadPoolUtil {
	private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
	private static final Integer poolThread = 50;  //默认初始化线程池最大线程为50个
	private static HashMap poolMap = new HashMap<>();
	
	
	public static void execute(Thread thread,String unitId){
				int maxThreads = 5;// 初始化默认最多5个线程
				 ThreadPoolExecutor pool = (ThreadPoolExecutor) poolMap.get(unitId);
				 if (pool == null) {
						pool = new ThreadPoolExecutor(maxThreads, poolThread, 2 * 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
						pool.allowCoreThreadTimeOut(true);
						poolMap.put(unitId, pool);
					
					}
				 while (true) {
						if (pool.getQueue().size() > 10) {
							try {
								System.out.println("队列已满，等500豪秒再添加任务");
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}//如果线程池队列还有未取出任务，当前线程阻塞，等待线程池队列降低后再执行
							pool = (ThreadPoolExecutor) poolMap.get(unitId);
						} else {
							break;
						}
					}
				 try{
						pool.allowCoreThreadTimeOut(true);
						pool.execute(thread);
					}catch (RejectedExecutionException e) {
						pool.purge();
						pool.shutdown();
						logger.error("运行改线程出现异常");
					}catch (Throwable e) {
						pool.purge();
						pool.shutdown();
						logger.error("运行改线程出现异常");
					}
		
	} 
}
