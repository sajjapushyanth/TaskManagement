package com.project.taskManagement.repository;


import com.project.taskManagement.entity.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<VerifyToken,Long> {
//    Optional<VerifyToken>findByToken(String token);
    Optional<VerifyToken> findByOtp(int otp);
}
