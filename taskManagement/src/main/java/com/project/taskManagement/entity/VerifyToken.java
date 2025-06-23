package com.project.taskManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VerifyToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Integer otp;

    @OneToOne
    @JoinColumn(nullable = false)
    private UserTable user;

    private LocalDateTime expiryDate;

}
