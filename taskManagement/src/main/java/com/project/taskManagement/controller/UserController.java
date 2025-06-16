package com.project.taskManagement.controller;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/task/get/{userId}")
    public List<TaskTable> getTasksByUserId(@PathVariable Long userId){
        return userService.getTasksByUserId(userId);

    }

    @PutMapping("/task/updateStatus/{taskId}")
    public ResponseEntity<Object> updateTasksStatus(@PathVariable Long taskId, @RequestBody TaskDto taskDto){
        TaskDto taskDto1=userService.updateTask(taskId,taskDto);
        return new ResponseEntity<>(taskDto1, HttpStatus.OK);
    }
}
