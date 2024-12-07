package com.hezron.Task.Manager.repository;

import com.hezron.Task.Manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCategory(String category);
}
