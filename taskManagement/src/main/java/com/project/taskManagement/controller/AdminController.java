package com.project.taskManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @PostMapping("/tast/create")
    public String createTask(){
        return "taskCreated";
    }



}
