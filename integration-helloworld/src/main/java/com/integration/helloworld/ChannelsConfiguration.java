package com.integration.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class ChannelsConfiguration {

    @Bean
    public SubscribableChannel greetingsChannel() {
        return new DirectChannel();
    }
}
