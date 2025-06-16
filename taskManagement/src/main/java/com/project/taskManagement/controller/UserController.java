package com.project.taskManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/tast/get")
    public String getTasks(){
        return "get tasks";
    }
}
