package com.stocktrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TradingSignalController {

	@RequestMapping("/member/tradingsignal")
	public String doGetTradingSignal() {
		return "tradingsignal";
	}
	
}
