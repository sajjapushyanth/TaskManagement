package com.project.taskManagement.repository;

import com.project.taskManagement.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<UserTable,Long> {
}
