package com.project.taskManagement.controller;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.dto.UserDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.service.TaskService;
import com.project.taskManagement.service.UserService;
import jakarta.validation.Valid;
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
    @GetMapping("/")
    public String Hello(){
        return "<html>"
                + "<head>"
                + "<title>SIMS Home Page</title>"
                + "</head>"
                + "<body>"
                + "<div style='text-align: center;'>"
                + "<h1 style='color: #3498db;'>Welcome to TaskManagement Home Page</h1>"
                + "<p style='font-size: 18px;'>Thank you for visiting our page.</p>"
                + "<hr>"


                + "</div>"
                + "</body>"
                + "</html>";
    }
    @PostMapping("/task/create")
    public ResponseEntity<String> createTask( @Valid @RequestBody TaskDto taskDto){
        try {
            String response = taskService.addTasks(taskDto);
            ResponseEntity<String> value = new ResponseEntity<>(response, HttpStatus.CREATED);
            return value;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser( @Valid @RequestBody UserDto userDto){
        try{
        String response=userService.createUser(userDto);
        ResponseEntity<String> value =new ResponseEntity<>(response, HttpStatus.CREATED);
        return value;}
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
