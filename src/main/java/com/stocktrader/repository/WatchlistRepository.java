package com.stocktrader.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.stocktrader.domain.Watchlist;

public interface WatchlistRepository extends CassandraRepository<Watchlist> {

	//TODO: Parameterize the watchlistcode
	@Query("select * from watchlist where watchlistcode='HK001' and symbol=?0")
	Watchlist findBySymbol(String symbol);

}