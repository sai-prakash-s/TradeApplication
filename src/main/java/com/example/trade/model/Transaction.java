package com.example.trade.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne
    @JoinColumn(name = "from_customer_id")
    private Customer fromCustomer;

    @ManyToOne
    @JoinColumn(name = "to_customer_id")
    private Customer toCustomer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String transactionType; // Full Transaction/Partial Transaction

    private int transferredQuantity;

    private int remainingQuantity;

    private String comment;

    // Constructors, getters, setters, etc.
}

