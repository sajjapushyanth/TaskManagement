package com.project.taskManagement.controller;

import com.project.taskManagement.dto.LoginDto;
import com.project.taskManagement.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<String> userLogin( @RequestParam String userName,@RequestParam String password){
        LoginDto loginDto=new LoginDto();
        loginDto.setUserName(userName);
        loginDto.setPassword(password);
        logger.info("in controller");
        String jwtToken=loginService.loginUserNamePass(loginDto);
        logger.info("in controller result: {}", jwtToken);
        return ResponseEntity.ok(jwtToken);

    }

}
