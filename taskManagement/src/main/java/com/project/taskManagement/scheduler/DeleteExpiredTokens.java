package com.project.taskManagement.scheduler;

import com.project.taskManagement.entity.VerifyOtp;
import com.project.taskManagement.repository.OtpRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class DeleteExpiredTokens {
    private static final Logger logger = LoggerFactory.getLogger(DeleteExpiredTokens.class);

    @Autowired
    private OtpRepo tokenRepo;
    
    @Scheduled(fixedRate = 60000)
    public void cleanUpExpiredTokens(){
        List<VerifyOtp> expiredOtps=tokenRepo.findAll().stream()
                .filter(otp -> otp.getTimestamp() != null)
                .filter(otp -> otp.getTimestamp().toLocalDateTime().plusMinutes(5).isBefore(LocalDateTime.now()))
                .toList();
        tokenRepo.deleteAll(expiredOtps);
        logger.info("Expired OTPs deleted: {}", expiredOtps.size());
        
    }
    
}
