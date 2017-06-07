package com.stocktrader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stocktrader.dto.Chart;

@Controller
public class ChartController {

	private Logger logger = LoggerFactory.getLogger(ChartController.class);
	
	private static final String URL_CHART = "/member/chart";
	private static final String DTO_CHART = "chart";
	private static final String V_CHART = "cloud";
	private static final String V_CLOUD_CHART = "cloudchart";
	
	@RequestMapping(value = URL_CHART, method = RequestMethod.GET)
	public String doGetChart(Model model) {
		
		Chart chart = new Chart();
		model.addAttribute(DTO_CHART, chart);
		logger.info(chart.toString());
		
		return V_CHART;
	}
	
	@RequestMapping(value = URL_CHART, method = RequestMethod.POST)
	public String doPostChart(@ModelAttribute(DTO_CHART) Chart chart, Model model) {
		model.addAttribute(DTO_CHART, chart);
		return V_CLOUD_CHART;
	}
	
}
