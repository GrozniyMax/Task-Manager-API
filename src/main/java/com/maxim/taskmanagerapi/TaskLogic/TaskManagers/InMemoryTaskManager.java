package com.maxim.taskmanagerapi.TaskLogic.TaskManagers;


import com.maxim.taskmanagerapi.TaskLogic.Tasks.SimpleTask;
import com.maxim.taskmanagerapi.TaskLogic.Tasks.Task;
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

    private Map<Long, Task> tasks = new HashMap<>();

    public Task getTask(Long id) {
        return tasks.get(id);
    }

    public InMemoryTaskManager(Integer number) {
        for (int i = 0; i < number; i++) {
            this.tasks.put(0L,new SimpleTask("SUI"+number));
        }
    }

    public InMemoryTaskManager() {
        this.tasks.put(0L,new SimpleTask("SUI0"));
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

    @Override
    public void update(Long id, Task task) {
        tasks.put(id, task);
    }

    @Override
    public List<Task> getTasks() {
        return tasks.values().stream().toList();
    }

}
