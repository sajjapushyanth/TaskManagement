package com.project.taskManagement.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendVerificationEmail(String to, String subject,String body) {
        try {
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            javaMailSender.send(message);
            System.out.println("Email sent successfully to: " + to);

        }
        catch (Exception e) {
            e.printStackTrace(); // ← Log the actual reason if it fails
            System.out.println("Failed to send email to: " + to);
        }
    }
}
