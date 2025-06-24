package com.project.taskManagement.controller;

import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.entity.VerifyOtp;
import com.project.taskManagement.repository.OtpRepo;
import com.project.taskManagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    OtpRepo tokenRepo;
    @Autowired
    UsersRepo usersRepo;
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam int otp){
        Optional<VerifyOtp>Verifyotp=tokenRepo.findByOtp(otp);
        if(!Verifyotp.isPresent()){
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        VerifyOtp verifyToken=Verifyotp.get();
        LocalDateTime generateTime=verifyToken.getTimestamp().toLocalDateTime();
        if(generateTime.plusMinutes(5).isBefore(LocalDateTime.now())){
            tokenRepo.delete(verifyToken);
            return ResponseEntity.badRequest().body("OTP expired");
        }
        UserTable user =verifyToken.getUser();
        user.setEmailVerified(true);
        usersRepo.save(user);
        return ResponseEntity.ok("User verified successfully");

    }
}
