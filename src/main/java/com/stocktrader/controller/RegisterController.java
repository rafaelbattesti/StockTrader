package com.stocktrader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stocktrader.dto.RegisterForm;

@Controller
@RequestMapping("/member")
public class RegisterController {
	
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String doGetRegisterForm(Model model) {
		
		//Controller uses the model to link view
		//Taglibs should be added to the jsp
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm", registerForm);
		logger.info(registerForm.toString());
		
		//registerForm added as an attribute of the view register
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doPostRegisterForm(@ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result) {
		if (result.hasErrors())
			return "register";
		logger.info(registerForm.toString());
		
		return "home";
	}

}
