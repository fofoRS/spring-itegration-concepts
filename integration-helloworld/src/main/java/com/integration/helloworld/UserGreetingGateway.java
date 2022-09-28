package com.integration.helloworld;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(
        defaultRequestChannel = "greetingsChannel"
)
public interface UserGreetingGateway {

    void publishUserName(String name);
}
