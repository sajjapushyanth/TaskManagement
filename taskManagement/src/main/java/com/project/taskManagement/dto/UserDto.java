package com.project.taskManagement.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    @NotBlank(message = "Username cannot be blank")
    private String userName;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character"
    )
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    private String role;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isEmailVerified = false;

}
