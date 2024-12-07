package com.hezron.Task.Manager.repository;

import com.hezron.Task.Manager.model.Category;
import com.hezron.Task.Manager.model.Task;
import com.hezron.Task.Manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUser(User user);

    List<Task> findByUserAndCategory(User user, Category category);

    List<Task> findByUserAndStatus(User user, String status);

    List<Task> findByUserAndTitleContaining(User user, String title);

    List<Task> findByUserAndDueDateBefore(User user, LocalDate dueDate);

}
