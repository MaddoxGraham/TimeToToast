package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDTO;
import com.maddoxgraham.TimeToToast.Mappers.HiddenUserTaskMapper;
import com.maddoxgraham.TimeToToast.Mappers.UserEventRoleMapper;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Repository.HiddenUserTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HiddenUserTaskService {

    @Autowired
    private final HiddenUserTaskRepository hiddenUserTaskRepository;
    private final UserService userService;
    private final TaskService taskService;

    // Trouver un HiddenUserTask par sa clé composite
    public Optional<HiddenUserTask> findByHiddenUserTaskKey(HiddenUserTaskKey hiddenUserTaskKey) {
        return hiddenUserTaskRepository.findByHiddenUserTaskKey(hiddenUserTaskKey);
    }


    // Trouver tous les HiddenUserTasks
    public List<HiddenUserTask> getAllHiddenUserTasks() {
        return hiddenUserTaskRepository.findAll();
    }

    public List<HiddenUserTaskDto> findTasksByUserId(Long idUser) {
        List<HiddenUserTask> hiddenUserTasks = hiddenUserTaskRepository.findAllByUserIdUser(idUser);
        List<HiddenUserTaskDto> hiddenUserTaskDtos = hiddenUserTasks.stream()
                .map(HiddenUserTaskMapper::toDto)
                .collect(Collectors.toList());

        return hiddenUserTaskDtos;
    }

    public HiddenUserTask addHiddenUserTask(HiddenUserTaskDto hiddenUserTaskDto){
        HiddenUserTask hiddenUserTask = HiddenUserTaskMapper.toEntity(hiddenUserTaskDto, userService, taskService);
        return hiddenUserTaskRepository.save(hiddenUserTask);
    }


    // Supprimer un HiddenUserTask par sa clé composite
    public boolean deleteByHiddenUserTaskKey(HiddenUserTaskDto dto, UserService userService, TaskService taskService ) {
        HiddenUserTask hiddenUserTask = HiddenUserTaskMapper.toEntity(dto,userService,taskService);
        HiddenUserTaskKey key = hiddenUserTask.getHiddenUserTaskKey();
        if (hiddenUserTaskRepository.existsById(key)){
            hiddenUserTaskRepository.deleteByHiddenUserTaskKey(key);
            return true;
        }else{
            return false;
        }

    }


}
