package com.maddoxgraham.TimeToToast.Services;


import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Repository.TaskRepository;
import com.maddoxgraham.TimeToToast.Repository.UserTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;

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

    public void deleteTask(Long idTask) {
        // Supprimez d'abord les UserTask associés à cette tâche
        List<UserTask> userTasks = userTaskRepository.findByUserTaskKey_IdTask(idTask);
        userTaskRepository.deleteAll(userTasks);
        taskRepository.deleteById(idTask);
    }

    public void deleteTaskOfEvent(Long idEvent) {
        List<Task> taskList = taskRepository.findByEvent_IdEvent(idEvent);
        for(Task task: taskList) {
            List<UserTask> userTaskList = userTaskRepository.findByUserTaskKey_IdTask(task.getIdTask());
            userTaskRepository.deleteAll(userTaskList);
            taskRepository.deleteById(task.getIdTask());
        }
    }
}
