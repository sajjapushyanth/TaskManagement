package com.project.taskManagement.service;

import com.project.taskManagement.dto.TaskDto;
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


    public String addTasks(TaskDto taskDto){
        TaskTable taskTable =new TaskTable();
        taskTable.setStatus(taskDto.getStatus());
        taskTable.setDescription(taskDto.getDescription());
        taskTable.setCreatedBy(taskDto.getCreatedBy());
        taskTable.setUserId(taskDto.getUserId());
        tasksRepo.save(taskTable);
    return "added tasks";

    }


}
