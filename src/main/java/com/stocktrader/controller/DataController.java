package com.stocktrader.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stocktrader.domain.StockQuote;
import com.stocktrader.dto.OhlcData;
import com.stocktrader.repository.StockQuoteRepository;
import com.stocktrader.util.StockQuoteMapper;

@RestController
@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
	
	private static Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	private StockQuoteRepository stockQuoteRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<OhlcData> symbolFeed(String symbol) {
		logger.info("Getting " + symbol + " prices");
		List<StockQuote> quotes = (List<StockQuote>) stockQuoteRepository.findLastNBySymbol(symbol, 250);
		
		//Helper method to convert quotes
		return StockQuoteMapper.mapDomainToOhlc(quotes);
	}
}
