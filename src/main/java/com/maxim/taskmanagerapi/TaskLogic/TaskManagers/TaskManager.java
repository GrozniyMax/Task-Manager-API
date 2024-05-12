package com.maxim.taskmanagerapi.TaskLogic.TaskManagers;


import com.maxim.taskmanagerapi.TaskLogic.Tasks.Task;

import java.util.List;

public interface TaskManager {




    /**
     * Finds task by id
     * @param id given task
     * @return task
     * @throws if task not found
     */
    //TODO specify checked exception
    public Task getTaskByID(Long id);

    /**
     * <p>If task not exists than add it</p>
     * <p>Else replaces task with given</p>
     * @param task given task
     */
    public void add(Task task);

    public void update(Long id, Task task);

    public List<Task> getTasks();

}
