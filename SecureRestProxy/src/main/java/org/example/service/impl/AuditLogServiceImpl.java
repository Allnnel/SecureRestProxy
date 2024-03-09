package org.example.service.impl;

import org.example.model.AuditLog;
import org.example.repository.AuditLogRepository;
import org.example.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository repository;
    @Autowired
    public AuditLogServiceImpl(AuditLogRepository repository) {
        this.repository = repository;
    }
    @Override
    public void save(AuditLog log) {
        repository.save(log);
    }

}
