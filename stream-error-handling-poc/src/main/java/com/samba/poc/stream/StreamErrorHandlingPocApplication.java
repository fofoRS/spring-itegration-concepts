package com.samba.poc.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan(basePackages = {"com.samba.poc.stream"})
public class StreamErrorHandlingPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamErrorHandlingPocApplication.class, args);
	}

}
