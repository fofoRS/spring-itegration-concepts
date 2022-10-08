package com.samba.poc.stream.service;

import com.samba.poc.stream.model.TransactionInitiatedEvent;
import com.samba.poc.stream.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionPublisherService {
    private final StreamBridge messagingBridge;
    private final String INIT_TRANSACTION_BINDING_CHANNEL = "initTransaction-out-0";

    @Autowired
    public TransactionPublisherService(StreamBridge messagingBridge) {
        this.messagingBridge = messagingBridge;
    }

    public UUID initTransaction(TransactionRequest transactionRequest) {
        TransactionInitiatedEvent initiatedEvent = new TransactionInitiatedEvent(transactionRequest);
        messagingBridge.send(INIT_TRANSACTION_BINDING_CHANNEL,initiatedEvent);
        return initiatedEvent.getTransactionId();
    }
}
