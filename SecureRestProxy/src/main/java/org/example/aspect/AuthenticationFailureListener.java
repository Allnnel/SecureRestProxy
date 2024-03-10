package org.example.aspect;

import java.util.Date;
import org.example.model.AuditLog;
import org.example.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
  private AuditLogService service;

  @Autowired
  public AuthenticationFailureListener(AuditLogService service) {
    this.service = service;
  }

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    String username = (String) event.getAuthentication().getPrincipal();
    String ipAddress = getIpAddress(event.getAuthentication().getDetails());
    Date date = new Date();
    service.save(new AuditLog(ipAddress, false, date, username, null, null));
  }

  private String getIpAddress(Object details) {
    if (details instanceof WebAuthenticationDetails) {
      return ((WebAuthenticationDetails) details).getRemoteAddress();
    }
    return "Unknown";
  }
}
