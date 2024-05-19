package com.maxim.taskmanagerapi.TaskManagers.OneClient;


import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
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
    public TaskDTO getTaskByID(Long id);

    /**
     * <p>If task not exists than add it</p>
     * <p>Else replaces task with given</p>
     * @param taskDTO given task
     */
    public void add(TaskDTO taskDTO);

    public void update(Long id, TaskDTO taskDTO);

    public List<TaskDTO> getTasks();

}
