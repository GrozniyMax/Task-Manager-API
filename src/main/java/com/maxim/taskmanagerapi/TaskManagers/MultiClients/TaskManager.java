package com.maxim.taskmanagerapi.TaskManagers.MultiClients;

import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;

import java.util.List;

public interface TaskManager {
    /**
     * Finds task by id
     * @param id given task
     * @return task
     * @throws if task not found
     */
    //TODO specify checked exception
    public TaskDTO getTaskByID(Long id);

    /**
     * <p>If task not exists than add it</p>
     * <p>Else replaces task with given</p>
     * @param taskDTO given task
     */
    public void add(TaskDTO taskDTO);

    public void update(Long id, TaskDTO taskDTO);

    public List<TaskDTO> getTasks(UserDAO userDAO);

}
