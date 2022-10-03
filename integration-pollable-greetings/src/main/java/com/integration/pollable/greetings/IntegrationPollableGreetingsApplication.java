package com.integration.pollable.greetings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;

@SpringBootApplication
public class IntegrationPollableGreetingsApplication implements CommandLineRunner {

	private final ConsoleUserGreetings consoleLineInput;
	private final GreetingsMessagingGateway greetingsMessagingGateway;

	public IntegrationPollableGreetingsApplication(
			ConsoleUserGreetings consoleLineInput, GreetingsMessagingGateway greetingsMessagingGateway) {
		this.consoleLineInput = consoleLineInput;
		this.greetingsMessagingGateway = greetingsMessagingGateway;
	}

	public static void main(String[] args) {
		SpringApplication.run(IntegrationPollableGreetingsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Function<String,String> callbackProcessor = greetingsMessagingGateway::buildGreeting;

		consoleLineInput.greetingToInputUserName(callbackProcessor);
	}
}
