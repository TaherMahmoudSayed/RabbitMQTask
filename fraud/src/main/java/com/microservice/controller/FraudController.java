package com.microservice.controller;

import com.microservice.clients.fraud.FraudCheckResponse;
import com.microservice.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud")
@RequiredArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;
    @GetMapping("check/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Long customerId){
        Boolean isFraud= fraudCheckService.isFraud(customerId);
        return new FraudCheckResponse(isFraud);
    }
}
