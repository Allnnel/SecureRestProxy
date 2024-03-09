package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ipAddress", nullable = false)
    private String ipAddress;
    @Column(name = "logged_in", nullable = false)
    private boolean loggedIn;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "method_name")
    private String methodName;
    @Column(name = "parameters")
    private String parameters;

    public AuditLog(String ipAddress, boolean loggedIn, Date date, String login, String methodName, String parameters) {
        this.ipAddress = ipAddress;
        this.loggedIn = loggedIn;
        this.date = date;
        this.login = login;
        this.methodName = methodName;
        this.parameters = parameters;
    }
}
