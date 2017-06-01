package com.stocktrader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages={"com.stocktrader"})
public class CassandraConfig extends AbstractCassandraConfiguration {
	
	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints("127.0.0.1");
		cluster.setPort(9042);
		
		return cluster;
	}
	
	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
		return new BasicCassandraMappingContext();
	}
	
	@Override
	protected String getKeyspaceName() {
		return "stocktrader";
	}

}
