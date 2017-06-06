package com.stocktrader.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktrader.domain.StockQuote;
import com.stocktrader.repository.StockQuoteRepository;

@Service
public class DataFeedService {
	
	private static Logger logger = LoggerFactory.getLogger(DataFeedService.class);
	
	@Autowired
	private StockQuoteRepository stockQuoteRepository;

	public BufferedReader getStockQuote(String symbol, int fromYear, int fromMonth, int fromDay, 
			int toYear, int toMonth, int toDay) {
		
		try {
					
			URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="
					+ symbol.toUpperCase() + "&f=d1ohgl1v");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			logger.info("Response code >>>>>>>>> " + String.valueOf(conn.getResponseCode()));
			logger.info("Initiate Connection to: " + url.toString());
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			
			return new BufferedReader(isr);
					
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
		return null;
	}
	
	public StockQuote parseStockQuote(String symbol, String[] feed) throws ParseException {
		
		Date priceDate = null;
		float priceOpen = 0;
		float priceHigh = 0;
		float priceLow = 0;
		float priceClose = 0;
		double volume = 0;
		
		//Remove quotes from date in CSV
		String priceDateString = feed[0];
		priceDateString = priceDateString.replaceAll("^\"|\"$", "");
		
		priceDate = new SimpleDateFormat("MM/dd/yyyy").parse(priceDateString);
		priceOpen = Float.parseFloat(feed[1]);
		priceHigh = Float.parseFloat(feed[2]);
		priceLow = Float.parseFloat(feed[3]);
		priceClose = Float.parseFloat(feed[4]);
		
		//Return POJO
		return new StockQuote(symbol.toUpperCase(), priceDate, priceOpen, priceHigh, priceLow, priceClose, volume);
	}
	
	public void getData(final String symbol) {
		
		final Calendar cal = Calendar.getInstance();
		
		//Today is the default date for TO data
		final Date today = new Date();
		cal.setTime(today);
		
		//Set the data retrieval parameters
		final int toYear = cal.get(Calendar.YEAR);
		final int toMonth = cal.get(Calendar.MONTH);
		final int toDay = cal.get(Calendar.DAY_OF_MONTH);
		
		//Set the data retrieval parameters
		StockQuote lastStockQuote = stockQuoteRepository.findLastBySymbol(symbol);
		Date lastStockQuoteDate = null;
		
		//Find the last quote from DB
		if (lastStockQuote != null) {
			lastStockQuoteDate = lastStockQuote.getPriceDate();
		}
		
		//Assign default parameters
		int fromYear = 2000;
		int fromMonth = 0;
		int fromDay = 1;

		if (lastStockQuoteDate != null) {
			cal.setTime(lastStockQuoteDate);
			cal.add(Calendar.DATE, 1);
			fromYear = cal.get(Calendar.YEAR);
			fromMonth = cal.get(Calendar.MONTH);
			fromDay = cal.get(Calendar.DAY_OF_MONTH);
		}
		
		//Retrieve Stock data from url
		final BufferedReader br = getStockQuote(symbol,fromYear, fromMonth, fromDay, toYear, toMonth, toDay);

		if (br != null) {
			
			logger.info("Reading stock: " + symbol);
			
			try {
				
				//Read the rest of the csv and save object
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					String[] feed = line.split(",", -1);
					StockQuote quote = parseStockQuote(symbol,feed);
					stockQuoteRepository.save(quote);
					logger.info(quote.toString() + "saved to database.");
				}
				
				logger.info("Data feed complete.");
				
			} catch (IOException | ParseException e) {
				logger.error(e.toString(), e);
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
		}	
	}
}
