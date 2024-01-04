package com.example.trade.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private Long boxId;

    private String boxName;

    private BigDecimal boxWeight;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private BoxStatus boxStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public BoxAudit(Long boxId, String boxName, BigDecimal boxWeight, Long customerId, BoxStatus boxStatus) {
        this.boxId = boxId;
        this.boxName = boxName;
        this.boxWeight = boxWeight;
        this.customerId = customerId;
        this.boxStatus = boxStatus;
    }

    @PrePersist
    public void prePersist() {
        this.timestamp = new Date();
    }
}

