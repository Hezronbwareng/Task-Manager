package com.hezron.Task.Manager.service;

import com.hezron.Task.Manager.model.Category;
import com.hezron.Task.Manager.model.Task;
import com.hezron.Task.Manager.model.User;
import com.hezron.Task.Manager.repository.CategoryRepository;
import com.hezron.Task.Manager.repository.TaskRepository;
import com.hezron.Task.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Task> getAllTasksForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUser(user);
    }

    public List<Task> getTasksByCategory(String username, String categoryName) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new RuntimeException("Category not found"));
        return taskRepository.findByUserAndCategory(user, category);
    }

    public List<Task> searchTasks(String username, String title, String status, LocalDate dueDate) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (title != null && !title.isEmpty()) {
            return taskRepository.findByUserAndTitleContaining(user, title);
        } else if (status != null && !status.isEmpty()) {
            return taskRepository.findByUserAndStatus(user, status);
        } else if (dueDate != null) {
            return taskRepository.findByUserAndDueDateBefore(user, dueDate);
        } else {
            return taskRepository.findByUser(user);
        }
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }



}
