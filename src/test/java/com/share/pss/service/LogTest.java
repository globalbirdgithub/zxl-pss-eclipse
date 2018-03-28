package com.share.pss.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**日志测试
 * @author MrZhang 
 * @date 2018年3月28日 下午12:12:39
 * @version V1.0
 */
public class LogTest {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void testName() throws Exception {
		logger.debug("");
		logger.info("");
		logger.warn("");
		logger.error("");
	}
}
