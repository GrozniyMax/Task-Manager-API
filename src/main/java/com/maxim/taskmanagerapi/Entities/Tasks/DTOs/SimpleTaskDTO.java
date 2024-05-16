package com.maxim.taskmanagerapi.Entities.Tasks.DTOs;

import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that represents base task
 * @author Taranenko Maxim
 */
public class SimpleTaskDTO implements TaskDTO {
    private static Long lastID=0L;

    private Long id;

    private String label;

    @Nullable
    private String description;

    private Status status;

    @Nullable
    private LocalDateTime startTime;

    @Nullable
    private LocalDateTime deadline;

    @Nullable
    private Map<String,String> atriibutes = new HashMap<>();
    //TODO Change Object to specific type

    /**
     * Creates task with given label
     * status will be NOT_STARTED
     * @param label - label of task
     */
    public SimpleTaskDTO(String label) {
        id = lastID++;
        this.label = label;
        this.status = Status.NOT_STARTED;
    }


    /**
     * Creates/replace existing attribure of task with value
     * @param key attribute name
     * @param value new value
     */
    public void setAtribute(String key, String value) {
        atriibutes.put(key, value);
    }

    public Object getAtribute(String key) {
        return atriibutes.get(key);
    }

    /**
     * Removes attribute by name
     * @param key name
     */
    public void removeAtribute(String key) {
        atriibutes.remove(key);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public Map<String, String> getAttributes() {
        return atriibutes;
    }

    public void setAttributes(@Nullable Map<String, String> attributes) {
        this.atriibutes = attributes;
    }
}
