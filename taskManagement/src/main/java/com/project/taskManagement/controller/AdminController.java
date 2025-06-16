package com.project.taskManagement.controller;

import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.service.TaskService;
import com.project.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @PostMapping("/task/create")
    public ResponseEntity<String> createTask(@RequestBody TaskTable taskTable){
        String response=taskService.addTasks(taskTable);
        ResponseEntity<String> value =new ResponseEntity<>(response, HttpStatus.CREATED);
        return value;
    }
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody UserTable userTable){
        String response=userService.createUser(userTable);
        ResponseEntity<String> value =new ResponseEntity<>(response, HttpStatus.CREATED);
        return value;
    }



}
