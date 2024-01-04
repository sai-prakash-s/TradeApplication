package com.example.trade.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long boxId;

    private Long fromCustomerId;

    private Long toCustomerId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // Full Transaction/Partial Transaction

    private BigDecimal transferredQuantity;

    private BigDecimal remainingQuantity;

    private String comment;

    @PrePersist
    public void prePersist() {
        this.timestamp = new Date();
    }
}

