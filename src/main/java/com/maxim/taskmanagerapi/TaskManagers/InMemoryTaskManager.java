package com.maxim.taskmanagerapi.TaskManagers;


import com.maxim.taskmanagerapi.Tasks.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents Task Managet
 */

@Component
public class InMemoryTaskManager implements TaskManager {

    private Map<Long, Task> tasks = new HashMap<>();

    public Task getTask(Long id) {
        return tasks.get(id);
    }


    /**
     * @see TaskManager#getTaskByID(Long)
     */
    @Override
    public Task getTaskByID(Long id) {
        return tasks.get(id);
    }

    /**
     * @see TaskManager#add(Task)
     */
    @Override
    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

}
