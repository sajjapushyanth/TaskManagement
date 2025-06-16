package com.project.taskManagement.controller;

import com.project.taskManagement.dto.LoginDto;
import com.project.taskManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login/{userId}")
    public String userLogin(@PathVariable Long userId, @RequestParam String userName,@RequestParam String password){
        LoginDto loginDto=new LoginDto();
        loginDto.setUserName(userName);
        loginDto.setPassword(password);
        System.out.println("in controller");
        String result=loginService.loginUserNamePass(userId,loginDto);
        return result;

    }

}
