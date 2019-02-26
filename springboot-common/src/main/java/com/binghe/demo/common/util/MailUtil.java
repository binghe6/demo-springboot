package com.binghe.demo.common.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 发邮件工具
 * 
 * 建议发邮件的流程：
 *  1、接收到发送邮件请求，首先记录请求并且入库。
	2、调用邮件发送接口发送邮件，并且将发送结果记录入库。
	3、启动定时系统扫描时间段内，未发送成功并且重试次数小于3次的邮件，进行再次发送
	
	Tips：
	很多时候邮件发送并不是我们主业务必须关注的结果，比如通知类、提醒类的业务可以允许延时或者失败。
	这个时候可以采用异步的方式来发送邮件，加快主交易执行速度，
	在实际项目中可以采用MQ发送邮件相关参数，监听到消息队列之后启动发送邮件。
	或者开启一个新线程去发送
 * @author dongsw
 *
 */
@Component
@Slf4j
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     * @param to 收件人邮箱地址
     * @param subject 邮件标题
     * @param content 邮件文字内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送HTML邮件
     * @param to 收件人邮箱地址
     * @param subject 邮件标题
     * @param content 邮件文字内容用HTML
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
        	log.error("发送html邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath 要作为附件的文件的完整路径
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = new File(filePath.trim()).getName();
//            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            // 添加多个附件可以使用多条 helper.addAttachment(fileName, file)
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
        	log.error("发送带附件的邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送静态资源（一般是图片）的邮件
     * @param to
     * @param subject
     * @param content 举例 <html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>
     * @param rscPath 要发送的图片的完整路径
     * @param rscId 是图片在html中的唯一标识cid。要保证唯一
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            // 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
        	log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}