package com.stocktrader.dto;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class WatchlistForm {

	//TODO: Add validation watchlistcode
	private String watchlistcode;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String symbol;

	//TODO: Add validation active
	private String active;

	public String getWatchlistcode() {
		return watchlistcode;
	}

	public void setWatchlistcode(String watchlistcode) {
		this.watchlistcode = watchlistcode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "WatchlistForm [watchlistcode=" + watchlistcode + ", symbol=" + symbol + ", active=" + active + "]";
	}
	
	
	
}
