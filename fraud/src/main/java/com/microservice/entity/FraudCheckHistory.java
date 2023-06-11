package com.microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import jakarta.persistence.*;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FraudCheckHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "fraud_id_sequence")
    @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;


}
