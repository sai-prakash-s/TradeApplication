package com.example.trade.repository;

import com.example.trade.model.BoxAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxAuditRepository extends JpaRepository<BoxAudit, Long> {
}
