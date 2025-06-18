package com.project.taskManagement.service;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.repository.UsersRepo;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TasksRepo tasksRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public String createUser(UserTable userTable){

        String encodedPassword = passwordEncoder.encode(userTable.getPassword());
        userTable.setPassword(encodedPassword);
        usersRepo.save(userTable);

        return "added user";

    }
    public List<TaskTable> getAllTasks(){
        return tasksRepo.findAll();
    }
    public List<UserTable> getAllUsers(){
        return usersRepo.findAll();
    }

    public List<TaskTable> getTasksByUserId(Long userId){
        return tasksRepo.findByUserId(userId);

    }

    public TaskDto updateTask(Long taskId, TaskDto taskDto){
        List<TaskTable>op=tasksRepo.findByTaskTableTaskId(taskId);
        logger.info("Finding tasks for taskId: {}", taskId);
        logger.debug("Tasks found: {}", op);
        if (op != null && !op.isEmpty()) {
            TaskTable existingTask=op.get(0);
            existingTask.setStatus(taskDto.getStatus());

            TaskTable savedTasks=tasksRepo.save(existingTask);
            TaskDto result=new TaskDto();
            result.setStatus(savedTasks.getStatus());
            return result;
        }
        else {
            logger.warn("No tasks found for taskId: {}", taskId);
            throw new RuntimeException("No tasks found for taskId: " + taskId);
        }


    }

    public boolean isDbUp() {
        try {
            tasksRepo.count();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
