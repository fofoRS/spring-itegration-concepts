package com.integration.pollable.greetings;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class UserGreetingsServiceActivator {

    @ServiceActivator(
            inputChannel = "greetingsPollableChannel",
            outputChannel = "greetingsDirectReplyChannel",
            poller = {@Poller(fixedDelay = "500")})
    public String buildGreetingMessage(String userName) {
        return String.format("Hello %s", userName);
    }
}
