package com.maxim.taskmanagerapi.Entities.Tasks.DTOs;

import java.util.*;
import java.util.stream.Stream;

/**
 * Class that represents complicated task:
 * Complicated task:
 * <li>has same attributes that {@link SimpleTaskDTO}</li>
 * <li>Stores links to subtask</li>
 * @author Taranenko Maxim
 */
public class ComplexTaskDTO extends SimpleTaskDTO {

    private HashMap<Long , TaskDTO> tasks;

    public ComplexTaskDTO(String name) {
        super(name);
    }

    /**
     * Adds new sub task
     * @param taskDTO given task
     */
    public void addSubTask(TaskDTO taskDTO) {
        if (!tasks.containsKey(this.getId())) {
            tasks.put(taskDTO.getId(), taskDTO);
        }
        //TODO add exception trowing if task already exists
    }

    public void removeTask(TaskDTO taskDTO) {
        tasks.remove(taskDTO);
    }

    public TaskDTO getTask(Long id) {
        return tasks.get(id);
    }

    /**
     * <p>Finds all subtasks of this complex task</p>
     * <b>THERE MUST BE NO CYCLES</b>
     * @return
     */
    public List<TaskDTO> getAllSubTasks() {
        return  tasks.values().stream().map(
                task -> {
                    if (task instanceof ComplexTaskDTO) {
                        return ((ComplexTaskDTO) task).getAllSubTasks();
                    }
                    return task;
                }
        ).flatMap(element -> {
            if (element instanceof TaskDTO) {
                return Stream.of((TaskDTO) element);
            }
            return ((List<TaskDTO>) element).stream();
        }).toList();
    }

    /**Returns a map of subtacks grouped by layer
     * @return Map[layer index, tasks]
     */
    public Map<Long,List<TaskDTO>> getSubTasksByLayer() {
        Map<Long,List<TaskDTO>> tasksByLayers = new HashMap<>();
        Queue<ComplexTaskDTO> toVisit = new LinkedList<>();
        toVisit.add(this);
        List<TaskDTO> layer = new LinkedList<>();
        Long layerId = 0L;
        while (!toVisit.isEmpty()){
            for (TaskDTO taskDTO :tasks.values()){
                if (taskDTO instanceof ComplexTaskDTO){
                    toVisit.add((ComplexTaskDTO) taskDTO);
                }
                layer.add(taskDTO);
            }
            tasksByLayers.put(layerId,layer);
        }
        return getSubTasksByLayer();
    }
}
