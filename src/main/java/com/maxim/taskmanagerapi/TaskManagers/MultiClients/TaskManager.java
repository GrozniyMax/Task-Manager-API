package com.maxim.taskmanagerapi.TaskManagers.MultiClients;

import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskManager {
    /**
     * Finds task by id
     * @param id given task
     * @return task
     * @throws if task not found
     */
    //TODO specify checked exception
    public TaskDTO getTaskDTOByID(Long id);

    public TaskDAO getTaskDAOByID(Long id);

    /**
     * <p>If task not exists than add it</p>
     * <p>Else replaces task with given</p>
     * @param taskDTO given task
     */
    public void add(TaskDTO taskDTO,UserDTO userDTO);

    public void update(Long id, TaskDTO taskDTO, UserDTO userDTO);

    public List<TaskDTO> getTasks(UserDAO userDAO);

}
