package com.project.taskManagement.service;

import com.project.taskManagement.dto.TaskDto;
import com.project.taskManagement.dto.UserDto;
import com.project.taskManagement.entity.TaskTable;
import com.project.taskManagement.entity.UserTable;
import com.project.taskManagement.entity.VerifyOtp;
import com.project.taskManagement.exception.UserAlreadyPresentException;
import com.project.taskManagement.helper.GenerateRandomNumber;
import com.project.taskManagement.repository.TasksRepo;
import com.project.taskManagement.repository.OtpRepo;
import com.project.taskManagement.repository.UsersRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
    OtpRepo tokenRepo;
    @Autowired
    EmailService emailService;

    public String createUser(UserDto userDto){
        String token= UUID.randomUUID().toString();
        Integer randomOtp= GenerateRandomNumber.generateOtp(100000,999999);
        VerifyOtp verifyToken=new VerifyOtp();

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
        verifyToken.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        verifyToken.setOtp(randomOtp);

        tokenRepo.save(verifyToken);

        emailService.sendVerificationEmail(userTable.getEmail(), "Verify your account OTP",html(randomOtp));

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
    public String html(Integer otp) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>TaskManagement OTP</title>"
                + "</head>"
                + "<body style='font-family: Arial, sans-serif; text-align: center; background-color: #f9f9f9; padding: 20px;'>"
                + "<h1 style='color: #3498db;'>Welcome to TaskManagement Home Page</h1>"
                + "<p style='font-size: 18px;'>Your OTP is: <strong style='color: #e74c3c;'>" + otp + "</strong></p>"
                + "<p style='font-size: 18px;'>Thank you for visiting our page.</p>"
                + "<hr style='border: 0; border-top: 1px solid #ddd;'>"
                + "<p style='font-size: 14px; color: #888;'>If you did not request this, please ignore this email.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
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
