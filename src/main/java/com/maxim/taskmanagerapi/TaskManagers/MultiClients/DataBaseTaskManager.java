package com.maxim.taskmanagerapi.TaskManagers.MultiClients;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.AttributesRepo;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.TaskRepo;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Entities.TaskAdapter;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DataBaseTaskManager implements TaskManager {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private AttributesRepo attributesRepo;


    @Override
    public TaskDTO getTaskByID(Long id) {
        return TaskAdapter.toDTO(
                taskRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public void add(TaskDTO taskDTO) {
        taskRepo.saveAndFlush(TaskAdapter.toDAO(taskDTO));
    }

    @Override
    public void update(Long id, TaskDTO taskDTO) {
        Long newId = taskRepo.findById(id).orElseThrow(NoSuchElementException::new).getId();
        var obj = TaskAdapter.toDAO(taskDTO);
        obj.setId(newId);
        taskRepo.saveAndFlush(obj);
    }

    @Override
    public List<TaskDTO> getTasks(UserDAO user) {
        return taskRepo.findByOwnerId(user).stream().map(TaskAdapter::toDTO).toList();
    }
}
