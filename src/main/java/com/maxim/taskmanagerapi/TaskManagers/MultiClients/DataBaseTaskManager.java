package com.maxim.taskmanagerapi.TaskManagers.MultiClients;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.AttributesRepo;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.TaskRepo;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.TaskAdapter;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DataBaseTaskManager implements TaskManager {
//TODO delete prototype after rewriting
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private AttributesRepo attributesRepo;


    @Override
    public TaskDTO getTaskDTOByID(Long id) {
        return TaskAdapter.toDTO(
                taskRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public TaskDAO getTaskDAOByID(Long id){
        return taskRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void add(TaskDTO taskDTO, UserDTO userDTO) {
        taskRepo.saveAndFlush(TaskAdapter.toDAO(taskDTO,userDTO));
    }

    @Override
    public void update(Long id, TaskDTO taskDTO, UserDTO userDTO) {
        Long newId = taskRepo.findById(id).orElseThrow(NoSuchElementException::new).getId();
        var obj = TaskAdapter.toDAO(taskDTO,userDTO);
        obj.setId(newId);
        taskRepo.saveAndFlush(obj);
    }

    @Override
    public List<TaskDTO> getTasks(UserDAO user) {
        return taskRepo.findByOwnerId(user).stream().map(TaskAdapter::toDTO).toList();
    }
}
