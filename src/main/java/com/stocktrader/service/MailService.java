package com.stocktrader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	private static Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	public void sendMailAlert() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("no-reply@stocktrader.com");
		message.setTo("me@rafaelbattesti.com");
		message.setSubject("Trading Signal from stocktrader.com");
		message.setText("Dear Rafael, Welcome to stock trader.");
		
		try {
			mailSender.send(message);
			logger.info(message.toString());
		} catch (MailException e) {
			logger.error(e.getMessage());
		}
		
	}

}
