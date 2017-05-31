package com.stocktrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stocktrader.service.MailService;

@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/mail")
	public String sendMail() {
		mailService.sendMailAlert();
		
		return "redirect:/home";
	}

}
