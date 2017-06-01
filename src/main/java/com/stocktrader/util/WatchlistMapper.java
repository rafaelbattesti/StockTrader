package com.stocktrader.util;

import org.springframework.stereotype.Component;

import com.stocktrader.domain.Watchlist;
import com.stocktrader.dto.WatchlistForm;

@Component
public class WatchlistMapper {
	
	public Watchlist map(WatchlistForm model) {
		Watchlist domain = new Watchlist();
		domain.setSymbol(model.getSymbol());
		
		return domain;
	}
	
	public WatchlistForm map(Watchlist domain) {
		WatchlistForm model = new WatchlistForm();
		model.setWatchlistcode(domain.getWatchlistcode());
		model.setSymbol(domain.getSymbol());
		model.setActive(domain.getActive());
		
		return model;
	}

}
