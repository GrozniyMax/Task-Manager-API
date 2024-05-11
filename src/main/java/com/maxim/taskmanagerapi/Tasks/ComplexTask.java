package com.maxim.taskmanagerapi.Tasks;

import java.util.*;
import java.util.stream.Stream;

/**
 * Class that represents complicated task:
 * Complicated task:
 * <li>has same attributes that {@link SimpleTask}</li>
 * <li>Stores links to subtask</li>
 * @author Taranenko Maxim
 */
public class ComplexTask extends SimpleTask {

    private HashMap<Long ,Task> tasks;

    public ComplexTask(String name) {
        super(name);
    }

    /**
     * Adds new sub task
     * @param task given task
     */
    public void addSubTask(Task task) {




        if (!tasks.containsKey(this.getId())) {
            tasks.put(task.getId(),task);
        }
        //TODO add exception trowing if task already exists
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Task getTask(Long id) {
        return tasks.get(id);
    }

    /**
     * <p>Finds all subtasks of this complex task</p>
     * <b>THERE MUST BE NO CYCLES</b>
     * @return
     */
    public List<Task> getAllSubTasks() {
        return  tasks.values().stream().map(
                task -> {
                    if (task instanceof ComplexTask) {
                        return ((ComplexTask) task).getAllSubTasks();
                    }
                    return task;
                }
        ).flatMap(element -> {
            if (element instanceof Task) {
                return Stream.of((Task) element);
            }
            return ((List<Task>) element).stream();
        }).toList();
    }

    /**Returns a map of subtacks grouped by layer
     * @return Map[layer index, tasks]
     */
    public Map<Long,List<Task>> getSubTasksByLayer() {
        Map<Long,List<Task>> tasksByLayers = new HashMap<>();
        Queue<ComplexTask> toVisit = new LinkedList<>();
        toVisit.add(this);
        List<Task> layer = new LinkedList<>();
        Long layerId = 0L;
        while (!toVisit.isEmpty()){
            for (Task task :tasks.values()){
                if (task instanceof ComplexTask){
                    toVisit.add((ComplexTask) task);
                }
                layer.add(task);
            }
            tasksByLayers.put(layerId,layer);
        }
        return getSubTasksByLayer();
    }
}
