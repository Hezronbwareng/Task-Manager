package com.hezron.Task.Manager.controller;


import com.hezron.Task.Manager.model.Task;
import com.hezron.Task.Manager.model.User;
import com.hezron.Task.Manager.repository.CategoryRepository;
import com.hezron.Task.Manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String getAllTasks(Model model, @AuthenticationPrincipal User user) {
        List<Task> tasks = taskService.getAllTasksForUser(user.getUsername());
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/category/{categoryName}")
    public String getTasksByCategory(@PathVariable String categoryName, Model model, @AuthenticationPrincipal User user) {
        List<Task> tasks = taskService.getTasksByCategory(user.getUsername(), categoryName);
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @PostMapping("/search")
    public String searchTasks(@RequestParam String title, @RequestParam String status, @RequestParam LocalDate dueDate, Model model, @AuthenticationPrincipal User user) {
        List<Task> tasks = taskService.searchTasks(user.getUsername(), title, status, dueDate);
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "create-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, @AuthenticationPrincipal User user) {
        taskService.createTask(task, user.getUsername());
        return "redirect:/tasks";
    }



}
