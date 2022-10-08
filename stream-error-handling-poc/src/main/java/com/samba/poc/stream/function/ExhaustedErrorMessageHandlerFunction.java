package com.samba.poc.stream.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samba.poc.stream.exception.TransactionException;
import com.samba.poc.stream.model.TransactionRejectedMessageWrapper;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ExhaustedErrorMessageHandlerFunction {

    private static final Logger logger = Logger.getLogger(ExhaustedErrorMessageHandlerFunction.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(
            inputChannel = "exhaustedTransactionalErrorChannel",
            outputChannel = "transactionMessageFailure-out-0"
    )
    public Message<String> exhaustedTransactionRejectedMessageHandler(ErrorMessage errorMessage)
            throws JsonProcessingException {
        Throwable rootCauseException = extractRootCauseException(errorMessage.getPayload());
        TransactionRejectedMessageWrapper wrapperPayload = new TransactionRejectedMessageWrapper(
                new String("Booooo!"),
                "InitService",
                rootCauseException.getMessage());
        return MessageBuilder
                .withPayload(objectMapper.writeValueAsString(wrapperPayload))
                .copyHeaders(errorMessage.getHeaders())
                .setHeader("contentType", "application/json").build();
    }

    private Long getRetryCount(Map<String,Object> headers) {
        List<Map<?, ?>> deathHeader = (List<Map<?,?>>) headers.get("x-death");
        return deathHeader.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getKey().equals("count"))
                .map(filteredEntry -> (Long)filteredEntry.getValue())
                .findFirst().orElse(0L);
    }

    private Throwable extractRootCauseException(Throwable a) {
        while (a != null && !(a instanceof ImmediateAcknowledgeAmqpException)) {
            a = a.getCause();
        }
        return a;
    }
}
