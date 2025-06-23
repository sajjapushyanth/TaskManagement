package com.project.taskManagement.service;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.dto.UserDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.entity.VerifyToken;
import com.project.taskManagement.exception.UserAlreadyPresentException;
import com.project.taskManagement.helper.GenerateRandomNumber;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.repository.TokenRepo;
import com.project.taskManagement.repository.UsersRepo;
import com.project.taskManagement.helper.GenerateRandomNumber;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TasksRepo tasksRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenRepo tokenRepo;
    @Autowired
    EmailService emailService;

    public String createUser(UserDto userDto){
        String token= UUID.randomUUID().toString();
        Integer randomOtp= GenerateRandomNumber.generateOtp(100000,999999);
        VerifyToken verifyToken=new VerifyToken();

        UserTable userTable=new UserTable();

        List<UserTable> users= usersRepo.findByUserName(userTable.getUserName());
        if(!users.isEmpty()){
            throw new UserAlreadyPresentException(userTable.getUserName());
        }
        if(usersRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyPresentException("Email already exists: " + userDto.getEmail(),true);
        }
        userTable.setUserName(userDto.getUserName());
        userTable.setRole(userDto.getRole());
        userTable.setEmail(userDto.getEmail());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userTable.setPassword(encodedPassword);
        usersRepo.save(userTable);
        verifyToken.setToken(token);
        verifyToken.setUser(userTable);
        verifyToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        verifyToken.setOtp(randomOtp);

        tokenRepo.save(verifyToken);

        String verifyUrl= "http://localhost:8080/api/auth/verify/"+randomOtp;
        emailService.sendVerificationEmail(userTable.getEmail(), "Verify your account Click to verify",verifyUrl);


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
