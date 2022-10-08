package com.samba.poc.stream.config;

import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.messaging.SubscribableChannel;

import java.util.List;
import java.util.Map;

@Configuration
public class ChannelsConfig {

    @Bean
    public SubscribableChannel exhaustedTransactionalErrorChannel() {
        return new DirectChannel();
    }


    @ServiceActivator(inputChannel = "errorChannel")
    @Bean
    public ErrorMessageExceptionTypeRouter exhaustedTransactionErrorMessageRouter() {
        ErrorMessageExceptionTypeRouter router = new ErrorMessageExceptionTypeRouter();
        router.setChannelMapping(ImmediateAcknowledgeAmqpException.class.getName(),"exhaustedTransactionalErrorChannel");
        return router;
    }
}
