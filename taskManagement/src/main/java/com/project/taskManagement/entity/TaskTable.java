package com.project.taskManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class TaskTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String status;
    private Long user_id;
    private String description;
    private String createdBy;

}
