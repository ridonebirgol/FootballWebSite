/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author RIDVAN
 */
public class SendMail implements Runnable {

    private String messageBody = "";

    public SendMail(String messageBody) {
        this.messageBody = messageBody;
    }

    private static final Logger logger = Logger.getLogger(SendMail.class);

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    private void SendMail() {
        try {
            // Step1
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            // Step2
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("ridvanbirgul@hotmail.com"));
            generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("ridvanbirgul@hotmail.com"));
            generateMailMessage.setSubject("FootbalWebService Mail");
            generateMailMessage.setContent(messageBody, "text/html");

            // Step3
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", "ridvanbirgul@gmail.com", "rB2064269");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void run() {
        SendMail();
    }
}
