package com.stocktrader.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.stocktrader.domain.StockQuote;
import com.stocktrader.dto.OhlcData;

@Component
public class StockQuoteMapper {
	
	private static Logger logger = LoggerFactory.getLogger(StockQuoteMapper.class);
	
	public static List<StockQuote> mapOhlcToDomain(List<OhlcData> modelList) {
		
		List<StockQuote> quoteList = new ArrayList<>();
		
		for (OhlcData model : modelList) {
			StockQuote stockquote = new StockQuote();
			try {
				stockquote.setPriceDate(DateFormat.getInstance().parse(model.getDate()));
				stockquote.setOpenPrice(Float.parseFloat(model.getOpen()));
				stockquote.setHighPrice(Float.parseFloat(model.getHigh()));
				stockquote.setLowPrice(Float.parseFloat(model.getLow()));
				stockquote.setVolume(Double.parseDouble(model.getVolume()));
			} catch (ParseException e) {
				logger.error(e.getMessage());
				break;
			}
			quoteList.add(stockquote);
		}
		return quoteList;
	}
	
	public static List<OhlcData>  mapDomainToOhlc(List<StockQuote> domainList) {
		
		List<OhlcData> ohlcList = new ArrayList<>();
		
		for (StockQuote domain : domainList) {
			OhlcData form = new OhlcData();
			form.setDate(convertDate(domain.getPriceDate()));
			form.setOpen(String.valueOf(domain.getOpenPrice()));
			form.setHigh(String.valueOf(domain.getHighPrice()));
			form.setLow(String.valueOf(domain.getLowPrice()));
			form.setClose(String.valueOf(domain.getClosePrice()));
			form.setVolume(String.valueOf(domain.getVolume()));
			ohlcList.add(form);
		}
		
		return ohlcList;
	}
	
	private static String convertDate (Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yy");
		return sdf.format(date);
	}
}
