package com.project.taskManagement.repository;

import com.project.taskManagement.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<UserTable,Long> {

    List<UserTable> findByUserName(String userName);
    boolean existsByEmail(String email);
}
