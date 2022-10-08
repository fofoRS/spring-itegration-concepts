package com.samba.poc.stream.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

public class TransactionInitiatedEvent extends  TransactionRequest {
    private final UUID transactionId;

    public TransactionInitiatedEvent() {
        this.transactionId = UUID.randomUUID();
    }

    public TransactionInitiatedEvent(TransactionRequest request) {
        super(request.getUserName(), request.getType());
        this.transactionId = UUID.randomUUID();
    }


    public UUID getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "TransactionInitiatedEvent{" +
                "transactionId=" + transactionId +
                "userName=" + getUserName() +
                '}';
    }
}
