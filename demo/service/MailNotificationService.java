package com.example.demo.service;

import javax.persistence.SecondaryTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.User;

@Service
public class MailNotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailNotificationService(JavaMailSender javaMailSender)
	{
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification(User user) throws MailException
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(user.getEmail());
		simpleMailMessage.setFrom("gokul.b.ponraj@gmail.com");
		simpleMailMessage.setSubject("Mail Sub");
		simpleMailMessage.setText("Welcome to CIMMS");
		
		javaMailSender.send(simpleMailMessage);
		
	}
	
	
}
