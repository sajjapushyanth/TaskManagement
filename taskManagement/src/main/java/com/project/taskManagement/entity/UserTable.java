package com.project.taskManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String role;
    private String email;


}
