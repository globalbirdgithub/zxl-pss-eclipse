package com.share.pss.service;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
/**定时调度测试
 * @author MrZhang 
 * @date 2018年5月7日 下午4:15:47
 * @version V1.0
 */
public class TimerTest {
	@Test
	public void timer() throws Exception {
		Timer timer = new Timer();//时间调度类
		timer.scheduleAtFixedRate(new TimerTask() {//TimerTask实现Runnable接口
			@Override
			public void run() {
				System.out.println(new Date());
			}
		}, 1000, 1000);//任务/延迟/间隔
		Thread.sleep(5000);
	}
}
