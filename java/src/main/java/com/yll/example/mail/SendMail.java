package com.yll.example.mail;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 发邮件
 * @author：linlin.yang
 * @date：2017/9/12 11:33
 */
public class SendMail {
    public static void main(String[] args) {
        try {
//            sendMailFromQQ();
            sendMailFromNetEase();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static void sendMailFromNetEase() throws GeneralSecurityException {
        final String userName = "iduomuer@163.com";
        final String password = "yll666";//动授权码
        String from = "iduomuer@163.com";
        String to = "1154486971@qq.com";
        String sendServer = "smtp.163.com";
        sendEmail(userName, password, from, to, sendServer);
    }

    public static void sendMailFromQQ() throws GeneralSecurityException {
        final String userName = "1154486971@qq.com";
        final String password = "iyfjruoiquyzbadd";//授权码
        String from = "1154486971@qq.com";
        String to = "iduomuer@163.com";
        String sendServer = "smtp.qq.com";
        sendEmail(userName, password, from, to, sendServer);
    }

    public static void sendEmail(final String userName, final String password, String from, String to, String sendServer) throws GeneralSecurityException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", sendServer);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");//465:SSL协议端口号,25:非SSL协议端口号
        props.put("mail.debug", "true");
        //开启ssl加密  qq邮箱必须设置
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        try {
            //创建会话
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            //创建邮件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("this is the subject line");
            //simple email
//            message.setText("this is content");
            //send html in email
//            message.setContent("<h1>this is content</h1>", "text/html");
            //send attachment in email
            Multipart multipart = new MimeMultipart();

            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText("this is message body");
            multipart.addBodyPart(bodyPart);

            bodyPart = new MimeBodyPart();
            String fileName = "test.txt";
            String filePath = SendMail.class.getResource("").getPath() + fileName;
            System.out.println(filePath);

            DataSource dataSource = new FileDataSource(filePath);
            bodyPart.setDataHandler(new DataHandler(dataSource));
            bodyPart.setFileName(fileName);
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);

            //发送邮件
            Transport.send(message);
            System.out.println("send message successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
