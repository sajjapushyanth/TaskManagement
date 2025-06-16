package com.project.taskManagement.service;

import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TasksRepo tasksRepo;


    public String addTasks(TaskTable taskTable){

    tasksRepo.save(taskTable);
    return "added tasks";

    }


}
