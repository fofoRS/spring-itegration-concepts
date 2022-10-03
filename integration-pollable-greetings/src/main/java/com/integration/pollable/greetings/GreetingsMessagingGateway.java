package com.integration.pollable.greetings;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(
        defaultRequestChannel = "greetingsPollableChannel",
        defaultReplyChannel = "greetingsDirectReplyChannel",
        defaultReplyTimeout = "1000")
public interface GreetingsMessagingGateway {

    @Gateway
    String buildGreeting(String userName);
}
