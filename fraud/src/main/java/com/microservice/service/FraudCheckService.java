package com.microservice.service;

import com.microservice.entity.FraudCheckHistory;
import com.microservice.repository.FraudCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudCheckRepository fraudRepository;

    public Boolean isFraud(Long customerId){
        fraudRepository.save(FraudCheckHistory.builder()
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .isFraudster(false).build());
        return false;
    }
}
