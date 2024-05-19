package com.maxim.taskmanagerapi.TaskManagers.OneClient;


import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.SimpleTaskDTO;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents Task Managet
 */

@Component
public class InMemoryTaskManager implements TaskManager {

    private Map<Long, TaskDTO> tasks = new HashMap<>();

    public TaskDTO getTask(Long id) {
        return tasks.get(id);
    }

    public InMemoryTaskManager(Integer number) {
        for (int i = 0; i < number; i++) {
            this.tasks.put(0L,new SimpleTaskDTO("SUI"+number));
        }
    }

    public InMemoryTaskManager() {
        this.tasks.put(0L,new SimpleTaskDTO("SUI0"));
    }

    /**
     * @see TaskManager#getTaskByID(Long)
     */
    @Override
    public TaskDTO getTaskByID(Long id) {
        return tasks.get(id);
    }

    /**
     * @see TaskManager#add(TaskDTO)
     */
    @Override
    public void add(TaskDTO taskDTO) {
        tasks.put(taskDTO.getId(), taskDTO);
    }

    @Override
    public void update(Long id, TaskDTO taskDTO) {
        tasks.put(id, taskDTO);
    }

    @Override
    public List<TaskDTO> getTasks() {
        return tasks.values().stream().toList();
    }

}
