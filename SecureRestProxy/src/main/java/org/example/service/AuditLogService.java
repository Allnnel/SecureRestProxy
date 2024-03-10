package org.example.service;

import org.example.model.AuditLog;

public interface AuditLogService {
  void save(AuditLog log);
}
