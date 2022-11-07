package com.example.demo.security.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloseableHttpClientConfig {

	  @Bean
	  public CloseableHttpClient httpClient() {
	    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
	    return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	  }
}
