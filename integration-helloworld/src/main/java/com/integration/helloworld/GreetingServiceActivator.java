package com.integration.helloworld;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceActivator {

    @ServiceActivator(inputChannel = "greetingsChannel")
    public void handleGreetingToUserName(String userName) {
        System.out.printf("Hello %s%n", userName);
    }
}
