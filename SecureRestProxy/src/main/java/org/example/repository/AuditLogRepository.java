package org.example.repository;

import org.example.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}
