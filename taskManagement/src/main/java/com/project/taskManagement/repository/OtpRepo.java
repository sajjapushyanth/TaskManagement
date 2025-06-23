package com.project.taskManagement.repository;


import com.project.taskManagement.entity.VerifyOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepo extends JpaRepository<VerifyOtp,Long> {
//    Optional<VerifyToken>findByToken(String token);
    Optional<VerifyOtp> findByOtp(int otp);
}
