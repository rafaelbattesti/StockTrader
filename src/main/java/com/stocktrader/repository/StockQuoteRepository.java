package com.stocktrader.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.stocktrader.domain.StockQuote;
import com.stocktrader.dto.OhlcData;

public interface StockQuoteRepository extends CassandraRepository<StockQuote>{

	@Query("Select * from stockquote where symbol = ?0 order by price_date desc limit 1")
	StockQuote findLastBySymbol(String symbol);

	@Query("Select * from stockquote where symbol = ?0 order by price_date desc limit ?1")
	List<StockQuote> findLastNBySymbol(String symbol, int limit);
}
