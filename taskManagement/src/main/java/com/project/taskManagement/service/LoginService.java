package com.project.taskManagement.service;

import com.project.taskManagement.config.JwtUtil;
import com.project.taskManagement.dto.LoginDto;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    public String loginUserNamePass(LoginDto loginDto){

        List<UserTable> users=usersRepo.findByUserName(loginDto.getUserName());
        System.out.println("in service");
        System.out.println("userName: " + loginDto.getUserName());

        if(users.size()>=1){
            UserTable existingCredentials=users.get(0);

            if( passwordEncoder.matches(loginDto.getPassword(),existingCredentials.getPassword())){

                return jwtUtil.generateToken(loginDto.getUserName());
            }
            else{
                return "Invalid Login";
            }

            }
        else {
            throw new RuntimeException("No user found with userName " + loginDto.getUserName());
        }

}}

