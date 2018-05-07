package com.share.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**邮件测试
 * @author MrZhang 
 * @date 2018年5月7日 下午5:04:29
 * @version V1.0
 */
public class MailTest {
	@Autowired
	MailSender mailSender;

	@Test
	public void mail() throws Exception {
		JavaMailSenderImpl sender =  (JavaMailSenderImpl)mailSender;
		//简单邮件对象
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		//发送人：和配置一致
		simpleMailMessage.setFrom("934017869@qq.com");
		//收件人
		simpleMailMessage.setTo("3452510253@qq.com");
		//主题
		simpleMailMessage.setSubject("牛皮大学录取通知书！");
		//内容
		simpleMailMessage.setText("你已经被录取");
		//设置回邮地址
		simpleMailMessage.setReplyTo("934017869@qq.com");
		//发送
		System.out.println(simpleMailMessage);
		System.out.println(sender);
		sender.send(simpleMailMessage);
	}
}
