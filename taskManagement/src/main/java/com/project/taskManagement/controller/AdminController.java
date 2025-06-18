package com.project.taskManagement.controller;

import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.service.TaskService;
import com.project.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/task/get/all")
    public List<TaskTable> getAllTasks(){
        return userService.getAllTasks();

    }
    @GetMapping("/users/get/all")
    public List<UserTable> getAllUsers(){
        return userService.getAllUsers();

    }


    @GetMapping(value = "/healthCheck", produces = MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        String appStatus = "UP";
        String dbStatus = "DOWN";
        try {
            if (userService.isDbUp()) {
                dbStatus = "UP";
            }
        } catch (Exception e) {
            dbStatus = "DOWN";
        }
        return "<html><body>"
                + "<h2>Application Status: " + appStatus + "</h2>"
                + "<h2>Database Status: " + dbStatus + "</h2>"
                + "</body></html>";
    }



}
