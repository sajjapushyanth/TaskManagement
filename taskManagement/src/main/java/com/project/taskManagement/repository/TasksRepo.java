package com.project.taskManagement.repository;

import com.project.taskManagement.entity.TaskTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepo extends JpaRepository<TaskTable,Long> {

    @Query("SELECT u from TaskTable u where u.userId=:userId")
    List<TaskTable> findByUserId(@Param("userId")Long userId);

}
