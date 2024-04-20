package com.etoak.java.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

@Component
public class EmailComponent {

    // 收件人电子邮箱
    String email = null;

    // 发件人电子邮箱
    @Value("${mail.smtp.from}")
    String from;
    // 指定发送邮件的主机
    @Value("${mail.smtp.host}")
    String host;
    // 设置邮件服务器端口号
    @Value("${mail.smtp.port}")
    String port;
    //设置密码/许可码
    @Value("${mail.smtp.password}")
    String password;
    //邮件默认标题
    @Value("${mail.smtp.default-head}")
    String defHead;


    // 邮件标题
    String head = null;
    // 邮件内容
    String content = null;


    public EmailComponent setEmail(String email){
        this.email = email;
        return this;
    }
    public EmailComponent setHead(String head){
        this.head = head;
        return this;
    }

    public EmailComponent setContent(String text){
        this.content = text;
        return this;
    }

    public void send(){
        try{
            if (email == null || "".equals(email)) {
                throw new RuntimeException("email address is empty!");
            }

            // 设置消息体
            Properties properties = getProperties();

            // 创建会话对象
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                    // 在这里填写发送邮件的邮箱地址和密码/授权码
                }
            });

            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));

            if(head != null && !"".equals(head)){
                message.setSubject(head);
            }else{
                message.setSubject(defHead);
            }

            // 设置消息体
            if(content != null && !"".equals(content)){
                message.setText(content);
            }else{
                throw new RuntimeException("content is empty!");
            }

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException | RuntimeException mex) {
            mex.printStackTrace();
        }

        // 清空当前邮件信息
        email = null;
        head = null;
        content = null;
    }

    private Properties getProperties() {
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true"); // 启用身份验证
        properties.put("mail.smtp.socketFactory.port", "465"); // 设置 SSL 端口
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 设置 SSL Socket Factory
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 禁用 SSL 回退
        return properties;
    }
}
