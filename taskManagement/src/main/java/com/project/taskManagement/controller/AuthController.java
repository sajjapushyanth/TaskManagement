package com.project.taskManagement.controller;

import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.entity.VerifyToken;
import com.project.taskManagement.repository.TokenRepo;
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
    TokenRepo tokenRepo;
    @Autowired
    UsersRepo usersRepo;
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam int otp){
//        Optional<VerifyToken> optionalToken= tokenRepo.findByToken(token);
        Optional<VerifyToken>Verifyotp=tokenRepo.findByOtp(otp);
        if(!Verifyotp.isPresent()){
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        VerifyToken verifyToken=Verifyotp.get();
        if(verifyToken.getExpiryDate().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("OTP expired");
        }
        UserTable user =verifyToken.getUser();
        user.setEmailVerified(true);
        usersRepo.save(user);
        return ResponseEntity.ok("User verified successfully");

    }
}
