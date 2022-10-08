package com.samba.poc.stream.function;

import com.samba.poc.stream.model.TransactionRejectedMessageWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Component
public class TransactionFailuresAuditHandlerFunction {

    Logger logger = Logger.getLogger(TransactionFailuresAuditHandlerFunction.class.getName());

    @Bean
    public Consumer<TransactionRejectedMessageWrapper> transactionFailureAudit() {
        return event -> logger.info("Failed Transaction Event received ---" + event );
    }
}
