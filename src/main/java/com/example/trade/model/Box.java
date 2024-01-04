package com.example.trade.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Box {

    @Id
    private Long id;

    private String boxName;

    private BigDecimal boxWeight;

    private Long customerId;

//    @Transient
//    private Map<String, String> changes = new HashMap<>();
//
//    @PrePersist
//    @PreUpdate
//    private void prePersistOrUpdate() {
//        // This method will be called before the entity is persisted or updated
//
//        // You can compare the current state with the previous state and record changes
//        if (this.id != null) {
//            Box previousState = boxRepository.findById(this.id).orElse(null);
//
//            if (previousState != null) {
//                compareAndRecordChange("boxName", previousState.getBoxName(), this.boxName);
//                compareAndRecordChange("boxWeight", previousState.getBoxWeight().toString(), this.boxWeight.toString());
//                compareAndRecordChange("customerId", previousState.getCustomerId().toString(), this.customerId.toString());
//            }
//        }
//    }
//
//    private void compareAndRecordChange(String fieldName, String oldValue, String newValue) {
//        if (!Objects.equals(oldValue, newValue)) {
//            changes.put(fieldName, "From '" + oldValue + "' to '" + newValue + "'");
//        }
//    }

}


