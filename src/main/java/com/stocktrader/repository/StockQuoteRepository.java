package com.stocktrader.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.stocktrader.domain.StockQuote;

public interface StockQuoteRepository extends CassandraRepository<StockQuote>{

	@Query("Select * from stockquote where symbol = ?0 order by price_date desc limit 1")
	StockQuote findLastBySymbol(String symbol);

}
