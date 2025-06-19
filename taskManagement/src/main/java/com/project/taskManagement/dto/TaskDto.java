package com.project.taskManagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class TaskDto {
    private Long taskId;
    @NotBlank(message = "Status cannot be blank")
    private String status;
    @NotNull(message = "User ID cannot be blank")
    private Long userId;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank(message = "Created By cannot be blank")
    private String createdBy;

}
