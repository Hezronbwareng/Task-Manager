package com.hezron.Task.Manager.controller;


import com.hezron.Task.Manager.model.Task;
import com.hezron.Task.Manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String viewTasks(Model model){
        model.addAttribute("tasks",taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/new")
    public String NewTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id,Model model){
        model.addAttribute("task", taskService.getTask(id));
        return "task-form";
    }

    @GetMapping("/delete{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "/redirect:/tasks";
    }
}
