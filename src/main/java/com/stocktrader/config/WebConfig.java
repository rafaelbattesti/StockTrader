package com.stocktrader.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WebConfig {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				
				container.addErrorPages(error401, error404, error500);
				
			}
			
		};
		
	}

}
