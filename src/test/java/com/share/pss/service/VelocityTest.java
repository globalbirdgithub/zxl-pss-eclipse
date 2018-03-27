package com.share.pss.service;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import com.share.pss.tool.VelocityMaker;

/**Velocity模板测试
 * @author MrZhang 
 * @date 2018年3月25日 下午3:37:44
 * @version V1.0
 */
public class VelocityTest {
	@Test
	public void helloVelocity() throws Exception {
		Velocity.init();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("text", "今天是3月25号");
		Template template = null;
		try {
			template = Velocity.getTemplate("velocityTemplate/hello.html", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("模板创建异常:"+e.getMessage());
		}
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		System.out.println(stringWriter);
		stringWriter.close();
	}
	@Test
	public void testN() throws Exception {
		VelocityMaker.createCode();
	}
}
