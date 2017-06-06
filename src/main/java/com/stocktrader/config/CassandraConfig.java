package com.stocktrader.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = { "com.stocktrader" })
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(env.getProperty("cassandra.contactPoints"));
		cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));

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
