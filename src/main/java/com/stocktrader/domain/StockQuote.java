package com.stocktrader.domain;

import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("stockquote")
public class StockQuote {
	
	@PrimaryKeyColumn(name = "symbol", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String symbol;
	
	@PrimaryKeyColumn(name = "price_date", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	private Date priceDate;
	
	@Column("open_price")
	private float openPrice;
	
	@Column("high_price")
	private float highPrice;
	
	@Column("low_price")
	private float lowPrice;
	
	@Column("close_price")
	private float closePrice;
	
	@Column("volume")
	private double volume;
	
	public StockQuote() {
		//Default constructor
	}

	public StockQuote(String symbol, Date priceDate, float openPrice, float highPrice, float lowPrice, float closePrice,
			double volume) {
		super();
		this.symbol = symbol;
		this.priceDate = priceDate;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}

	public float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(float highPrice) {
		this.highPrice = highPrice;
	}

	public float getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(float lowPrice) {
		this.lowPrice = lowPrice;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockQuote [symbol=" + symbol + ", priceDate=" + priceDate + ", openPrice=" + openPrice + ", highPrice="
				+ highPrice + ", lowPrice=" + lowPrice + ", closePrice=" + closePrice + ", volume=" + volume + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(closePrice);
		result = prime * result + Float.floatToIntBits(highPrice);
		result = prime * result + Float.floatToIntBits(lowPrice);
		result = prime * result + Float.floatToIntBits(openPrice);
		result = prime * result + ((priceDate == null) ? 0 : priceDate.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		long temp;
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockQuote other = (StockQuote) obj;
		if (Float.floatToIntBits(closePrice) != Float.floatToIntBits(other.closePrice))
			return false;
		if (Float.floatToIntBits(highPrice) != Float.floatToIntBits(other.highPrice))
			return false;
		if (Float.floatToIntBits(lowPrice) != Float.floatToIntBits(other.lowPrice))
			return false;
		if (Float.floatToIntBits(openPrice) != Float.floatToIntBits(other.openPrice))
			return false;
		if (priceDate == null) {
			if (other.priceDate != null)
				return false;
		} else if (!priceDate.equals(other.priceDate))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		return true;
	}
}
