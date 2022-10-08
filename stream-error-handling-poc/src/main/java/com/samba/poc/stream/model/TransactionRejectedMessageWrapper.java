package com.samba.poc.stream.model;


import java.io.Serializable;

public class TransactionRejectedMessageWrapper {
    private String payload;
    private String source;
    private String errorMessage;

    public TransactionRejectedMessageWrapper(){}

    public TransactionRejectedMessageWrapper(String payload, String source, String errorMessage) {
        this.payload = payload;
        this.source = source;
        this.errorMessage = errorMessage;
    }


    public String getPayload() {
        return payload;
    }

    public String getSource() {
        return source;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
