package com.example.demo.service;

import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String SERVIDOR_SMTP = "smtp.office365.com";
    private static final int PORTA_SERVIDOR_SMTP = 587;
    private static final String CONTA_PADRAO = "cimsadmin@qms.com.my";
    private static final String SENHA_CONTA_PADRAO = "C1ms@dmin10";
    
    
    private final String from = "cimsadmin@qms.com.my";
    //private final String toList = "gokul.b.ponraj.java@gmail.com,misexec1@qms.com.my";

    //private final String subject = "!!! CIMS Reset Password !!!";
    //private final String messageContent = "Test Mail from CIMS";

    public void sendEmail(String name, String toMail, String key) {
    	final String toList = toMail;
    	final String subject = "Password Reset For QUBICS";
    	final String messageContent = "Dear " + name +",\n" + "Your Password Reset Key for QUBICS is :  " + key;
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            StringTokenizer tokenizer = new StringTokenizer(toList, ",");
            
            
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            
            while (tokenizer.hasMoreTokens()) {
            	
            	message.setRecipient(Message.RecipientType.TO, new InternetAddress(tokenizer.nextToken()));
            	Transport.send(message);
            }
            
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    }

    public static void main(final String[] args) {
        new TestMail().sendEmail("","","");
    }

}

