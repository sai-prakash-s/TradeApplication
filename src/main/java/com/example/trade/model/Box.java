package com.example.trade.model;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boxName;

    // Many boxes can belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Constructors, getters, setters, etc.
}


