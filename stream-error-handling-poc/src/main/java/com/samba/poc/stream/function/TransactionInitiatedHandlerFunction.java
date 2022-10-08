package com.samba.poc.stream.function;

import com.samba.poc.stream.exception.TransactionException;
import com.samba.poc.stream.model.TransactionInitiatedEvent;
import com.samba.poc.stream.model.TransactionRequest;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Component
public class TransactionInitiatedHandlerFunction {

    Logger log = Logger.getLogger(TransactionInitiatedHandlerFunction.class.getName());

    @Bean
    public Consumer<Message<TransactionInitiatedEvent>> transactionInitiatedHandler() {
        return event -> {
            log.info("Event received with Id: " + event.getPayload().getTransactionId());
            if (event.getPayload().getType().equals(TransactionRequest.TransactionType.ERROR)) {
                Map<String, Object> headers = event.getHeaders();
                if (headers.containsKey("x-death")) {
                    Long count = getRetryCount(headers);
                    if(count >= 3) {
                        throw new ImmediateAcknowledgeAmqpException("Retry exhausted");
                    }
                }
                throw new AmqpRejectAndDontRequeueException("Retrying");
            }
            log.info(String.format("Logging transactionEvent payload --- %s", event));
        };
    }

    private Long getRetryCount(Map<String,Object> headers) {
        List<Map<?, ?>> deathHeader = (List<Map<?,?>>) headers.get("x-death");
        return deathHeader.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getKey().equals("count"))
                .map(filteredEntry -> (Long)filteredEntry.getValue())
                .findFirst().orElse(0L);
    }
}
