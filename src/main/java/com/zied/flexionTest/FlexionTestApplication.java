package com.zied.flexionTest;

import com.flexionmobile.codingchallenge.integration.Integration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import javax.jnlp.IntegrationService;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FlexionTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexionTestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
