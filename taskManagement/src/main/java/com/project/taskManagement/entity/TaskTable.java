package com.project.taskManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Tasks")
public class TaskTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String status;
    private Long userId;
    private String description;
    private String createdBy;


}
