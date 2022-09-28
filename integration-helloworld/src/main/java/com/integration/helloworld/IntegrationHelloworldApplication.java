package com.integration.helloworld;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
@Configuration
@IntegrationComponentScan(basePackages = {"com.integration.helloworld"})
@EnableIntegration
@SpringBootApplication
public class IntegrationHelloworldApplication implements ApplicationRunner {

	private final UserGreetingGateway userGreetingGateway;

	public IntegrationHelloworldApplication(UserGreetingGateway userGreetingGateway) {
		this.userGreetingGateway = userGreetingGateway;
	}

	public static void main(String[] args) {
		SpringApplication.run(IntegrationHelloworldApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userGreetingGateway.publishUserName("Rodolfo");
	}
}
