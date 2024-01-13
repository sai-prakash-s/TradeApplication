package com.example.trade.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Box {

    @Id
    private Long id;

    private String boxName;

    private BigDecimal boxWeight;

    private Long customerId;

}


