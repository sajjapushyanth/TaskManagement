package com.project.taskManagement.dto;


import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String password;
    private String role;
    private String email;

}
