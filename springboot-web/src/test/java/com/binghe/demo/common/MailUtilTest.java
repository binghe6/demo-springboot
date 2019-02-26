package com.binghe.demo.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.binghe.demo.common.util.MailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {

	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Test
	public void test() {
		mailUtil.sendSimpleMail("dongsunwei@ichangtou.com", "这是简单邮件的标题", "这是简单邮件的内容");
	}
	
	@Test
	public void testHtmlMail() {
		String content="<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
		mailUtil.sendHtmlMail("dongsunwei@ichangtou.com","html邮件",content);
	}
	
	@Test
	public void sendAttachmentsMail() {
	    String filePath="C:/Users/binghe/Desktop/binghe_demo.sql";
	    mailUtil.sendAttachmentsMail("dongsunwei@ichangtou.com", "带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
	public void sendInlineResourceMail() {
	    String rscId = "neo006";
	    String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
	    String imgPath = "C:/Users/binghe/Desktop/temp.jpg";

	    mailUtil.sendInlineResourceMail("dongsunwei@ichangtou.com", "这是有图片的邮件", content, imgPath, rscId);
	}
	
	@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("id", "006");
	    // 第一个参数是作为模版的html文件名
	    String emailContent = templateEngine.process("emailTemplate", context);

	    mailUtil.sendHtmlMail("dongsunwei@ichangtou.com","主题：这是模板邮件",emailContent);
	}
}
