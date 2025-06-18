package com.project.taskManagement.dto;


import lombok.Data;

@Data
public class TaskDto {
    private Long taskId;
    private String status;
    private Long userId;
    private String description;
    private String createdBy;

}
