package com.okdeer.mall.email;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.security.NoSuchProviderException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.jni.Address;
import org.jboss.logging.Message;

/**
 * 发送邮件代码
 * @author Administrator
 *
 */
public class TestSendEmail {

    public static void main(String[] args) {
        
        //声明主机名和发送邮件的地址
        String host = "smtp.163.com";        //所使用的mail服务器
        String from = "";                    //发件人地址
        String to = "";                      //收件人地址
        
            
        // 设定所要用的Mail 服务器和所使用的传输协议
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", host);             //设定了使用的mail服务器
        pro.put("mail.transport.protocol", "smtp");  //设定了服务器使用的协议
        pro.put("mail.smtp.auth", "true");             //指定是否需要smtp验证
        
        //验证是否通过
        Authenticator  auth = new MyAuthenticator();
       //得到新的session服务
        javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(pro, auth);
        
        //定义邮件内容
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setContent("Hello,测试邮件", "text/plain;charset=GBk");     //如果message所使 用的内容是文本的话，那么可以使用message.setContent（‘hello’）来直接传入值
            
            //设定主题
            message.setSubject("didi");
            
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        //设置邮件的地址类，使用Address的InternetAddress子类 ,可以通过传递两个参数：代表邮件地址和名字的字符串来建立一个具有邮件地址和名字的邮件地址类
        try {
            Address address = new InternetAddress(from,"");     //设定发送者姓名
            
            //设置邮件的发信人
            message.setFrom(address);        
//或者message.setReplyTo(address);如果在邮件中存在多个发信人地址，我们可用addForm()方法增加发信人，Address address[] = ...;            message.addFrom(address);  
            
            //设置邮件的收信人
            Address toAddress = new InternetAddress(to);
            
            //设置的时候有三个常量，Message.RecipientType.TO,Message.RecipientType.CC,Message.RecipientType.BCC,来指定邮件接收人首要发送人、抄送人、秘密抄送人
            message.addRecipient(Message.RecipientType.TO, toAddress);
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         try {
              //設定郵箱的代理服务器
            Transport transport = mailSession.getTransport("smtp");
            //填写上发送者邮箱的账号和密码，和使用的mail服务器
            transport.connect(host, "username", "password");
            //执行发送邮件任务
             transport.sendMessage(message,message.getAllRecipients());
            //关闭
             transport.close();  
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            System.out.println("您的用户名和密码不正确");
        }
        
        
        
    }
}

