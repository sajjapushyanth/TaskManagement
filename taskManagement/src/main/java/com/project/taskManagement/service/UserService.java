package com.project.taskManagement.service;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TasksRepo tasksRepo;
    public String createUser(UserTable userTable){

        usersRepo.save(userTable);
        return "added user";

    }
    public List<TaskTable> getTasksByUserId(@PathVariable Long userId){
        return tasksRepo.findByUserId(userId);

    }

    public TaskDto updateTask(Long taskId, TaskDto taskDto){
        List<TaskTable>op=tasksRepo.findByTaskTableTaskId(taskId);
        System.out.println("hello");
        System.out.println(op);
        if (op != null && !op.isEmpty()) {
            TaskTable existingTask=op.get(0);
            existingTask.setStatus(taskDto.getStatus());

            TaskTable savedTasks=tasksRepo.save(existingTask);
            TaskDto result=new TaskDto();
            result.setStatus(savedTasks.getStatus());
            return result;
        }
        else{
            throw new RuntimeException("No tasks found for taskId: " + taskId);
        }


    }

}
