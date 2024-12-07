package com.hezron.Task.Manager.service;

import com.hezron.Task.Manager.model.Task;
import com.hezron.Task.Manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return  taskRepository.findAll();
    }

    public Task getTask(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskByCategory(String category){
        return taskRepository.findByCategory(category);
    }

}
