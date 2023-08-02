package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateTask(Task task){
        return taskRepository.save(task);
    }

    public Task findTaskByIdTask(Long idTask){
        return taskRepository.findTaskByIdTask(idTask).orElseThrow(() -> new UserNotFoundException("User n° " + idTask + " was not found"));
    }

    public void deleteTask(Long idTask){
        taskRepository.deleteTaskByIdTask(idTask);
    }
}
