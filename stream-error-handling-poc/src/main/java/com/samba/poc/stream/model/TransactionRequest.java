package com.samba.poc.stream.model;


import com.fasterxml.jackson.annotation.JsonValue;

public class TransactionRequest {
    private String userName;
    private TransactionType type;


    public TransactionRequest(){}

    public TransactionRequest(String userName, TransactionType type) {
        this.userName = userName;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public TransactionType getType() {
        return type;
    }

    public enum TransactionType {
        ERROR,SUCCESS;

        @JsonValue
        public String getMeters() {
            return this.toString();
        }
    }
}


