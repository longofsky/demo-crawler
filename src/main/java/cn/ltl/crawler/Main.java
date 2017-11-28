package cn.ltl.crawler;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ltl.crawler.thread.ThreadPool;

/**
 * 爬虫程序入口
 * 
 * @author ltl
 *
 * @date 2017年11月10日 下午2:39:10
 *
 * @version V1.0
 */
public class Main {
	
	public static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
		
		//从Spring容器中获取到所有可以执行的爬虫,并且放到线程池中执行
		Map<String, Crawler>  map = applicationContext.getBeansOfType(Crawler.class);
		for (Crawler crawler : map.values()) {
			ThreadPool.runInThread(crawler);
		}
		
	}

}
