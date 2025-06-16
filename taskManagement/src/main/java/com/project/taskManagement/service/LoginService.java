package com.project.taskManagement.service;

import com.project.taskManagement.dto.LoginDto;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UsersRepo usersRepo;

    public String loginUserNamePass(Long userId,LoginDto loginDto){
        System.out.println("beff");
        Optional<UserTable> op=usersRepo.findById(userId);
        System.out.println("hellll");
        System.out.println(op);
        if(op!=null &&!op.isEmpty()){
            UserTable existingCredentials=op.get();
            System.out.println("before if");
            if(loginDto.getUserName().equals(existingCredentials.getUserName()) &&
                loginDto.getPassword().equals(existingCredentials.getPassword())){
                System.out.println("in if");
                return "Valid Login";
            }
            else{
                return "Invalid Login";
            }

            }
        else {
            throw new RuntimeException("No user found with id " + userId);
        }

}}
