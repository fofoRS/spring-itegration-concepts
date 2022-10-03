package com.integration.pollable.greetings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class ChannelsConfiguration {

    @Bean
    public PollableChannel greetingsPollableChannel() {
        return new QueueChannel(10);
    }

    @Bean
    public SubscribableChannel greetingsDirectReplyChannel() {
        return new DirectChannel();
    }
}
