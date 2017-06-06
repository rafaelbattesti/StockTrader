package com.stocktrader.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stocktrader.repository.WatchlistRepository;
import com.stocktrader.service.DataFeedService;
import com.stocktrader.domain.Watchlist;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:scheduler.properties" })
public class DataFeedServiceJob {
	
	private static Logger logger = LoggerFactory.getLogger(DataFeedServiceJob.class);
	
	@Autowired
	private DataFeedService service;
	
	@Autowired
	private WatchlistRepository watchlistRepository;
	
	@Autowired
	private Environment env;
	
	@Scheduled(fixedDelayString = "${com.stocktrader.fixedrate}")
	public void runDataFeedService() {
		boolean isRun = Boolean.valueOf(env.getProperty("com.stocktrader.runDataFeedService").toUpperCase());
		
		if (isRun) {

			String watchlistcode = env.getProperty("com.stocktrader.watchlistcode");
			
			List<Watchlist> watchlists = watchlistRepository.findByWatchlistcode(watchlistcode);
			
			for (Watchlist watchlist : watchlists) {
				logger.info("Start getting " + watchlist.getSymbol() + " data.");
				service.getData(watchlist.getSymbol());
				logger.info("Finish getting " + watchlist.getSymbol() + " data.");
			}
			
		}
	}

}
