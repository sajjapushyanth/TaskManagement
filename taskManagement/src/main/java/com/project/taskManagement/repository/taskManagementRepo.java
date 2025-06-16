package com.project.taskManagement.repository;

import com.project.taskManagement.entity.TaskTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface taskManagementRepo extends JpaRepository<TaskTable,Long> {


}
