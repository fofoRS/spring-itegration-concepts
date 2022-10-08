package com.samba.poc.stream.controller;

import com.samba.poc.stream.model.TransactionRequest;
import com.samba.poc.stream.service.TransactionPublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TransactionController {

    private final TransactionPublisherService transactionService;

    public TransactionController(TransactionPublisherService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/v1/transaction")
    public ResponseEntity<Map<String,String>> iniTransactionType(@RequestBody TransactionRequest transactionRequest) {
        UUID transactionId = transactionService.initTransaction(transactionRequest);
        Map<String,String> response = new HashMap<>();
        response.put("transactionId", transactionId.toString());
        return ResponseEntity.ok(response);
    }
}
