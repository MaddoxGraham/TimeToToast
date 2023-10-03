package com.maddoxgraham.TimeToToast.Services;


import com.maddoxgraham.TimeToToast.DTOs.NewTaskDto;
import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.DTOs.UserTaskDto;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.TaskMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Repository.EventRepository;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import com.maddoxgraham.TimeToToast.Repository.TaskRepository;
import com.maddoxgraham.TimeToToast.Repository.UserTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;
    private final TaskMapper taskMapper;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    public Task addTask(NewTaskDto newTaskDto){
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(newTaskDto.getDescription());
        taskDto.setDateTask(newTaskDto.getDueDate());
        taskDto.setUrgence(newTaskDto.getUrgency());
        taskDto.setEvent(newTaskDto.getEvent());
        taskDto.setCreator(newTaskDto.getCreator());
        Optional<Person> personOpt = personRepository.findByidPerson(newTaskDto.getCreator());
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            Optional<Event> eventOpt = eventRepository.findEventByIdEvent(newTaskDto.getEvent());
            if(eventOpt.isPresent()){
                Event event = eventOpt.get();
                Task task = taskRepository.save(taskMapper.toEntity(taskDto, person, event));
                return task;
            }
        }
        return null;
    }

    public List<NewTaskDto> findAllTasks(Long idEvent){
        List<Task> taskList = taskRepository.findByEvent_IdEvent(idEvent);
        List<NewTaskDto> newTaskDtos = new ArrayList<>();
        for (Task task: taskList){
            List<UserTask> userTaskList = userTaskRepository.findByUserTaskKey_IdTask(task.getIdTask());
            NewTaskDto newTaskDto = new NewTaskDto();
            newTaskDto.setIdTask(task.getIdTask());
            newTaskDto.setDueDate(task.getDateTask());
            newTaskDto.setUrgency(task.getUrgence());
            newTaskDto.setDescription(task.getDescription());
            newTaskDto.setCreator(task.getCreator().getIdPerson());
            newTaskDto.setEvent(idEvent);
            List<Long> assignee = new ArrayList<>();
            List<Long> invisibleTo = new ArrayList<>();
            for(UserTask userTask: userTaskList) {
                if(!userTask.getIsInvisible()) {
                    assignee.add(userTask.getPerson().getIdPerson());
                } else {
                    invisibleTo.add(userTask.getPerson().getIdPerson());
                }
            }
            newTaskDto.setAssignee(assignee.toArray(new Long[0]));
            newTaskDto.setInvisibleTo(invisibleTo.toArray(new Long[0]));
            newTaskDtos.add(newTaskDto);
        }
        return newTaskDtos;
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
