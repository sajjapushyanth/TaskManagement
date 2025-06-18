package com.project.taskManagement.service;

import com.project.taskManagement.config.JwtUtil;
import com.project.taskManagement.dto.LoginDto;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.UsersRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private static final Logger logger = LogManager.getLogger(LoginService.class);

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    public String loginUserNamePass(LoginDto loginDto){

        List<UserTable> users=usersRepo.findByUserName(loginDto.getUserName());
        logger.info("In LoginService.loginUserNamePass");
        logger.debug("userName: {}", loginDto.getUserName());

        if(users.size()>=1){
            UserTable existingCredentials=users.get(0);

            if( passwordEncoder.matches(loginDto.getPassword(),existingCredentials.getPassword())){

                return jwtUtil.generateToken(loginDto.getUserName());
            }
            else {
                logger.warn("Invalid login attempt for userName: {}", loginDto.getUserName());
                return "Invalid Login";
            }
        } else {
            logger.error("No user found with userName: {}", loginDto.getUserName());
            throw new RuntimeException("No user found with userName " + loginDto.getUserName());
        }

}}

