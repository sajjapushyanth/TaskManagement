package com.project.taskManagement.controller;

import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/task/get/{userId}")
    public List<TaskTable> getTasksByUserId(@PathVariable Long userId){
        return userService.getTasksByUserId(userId);



    }
}
