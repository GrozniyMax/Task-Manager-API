package com.maxim.taskmanagerapi.Tasks;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that represents base task
 * @author Taranenko Maxim
 */
public class SimpleTask implements Task {
    private static Long lastID=0L;


    private Long id;
    private String label;
    private Status status;

    private Map<String,Object> atriibutes = new HashMap<>();
    //TODO Change Object to specific type

    /**
     * Creates task with given label
     * status will be NOT_STARTED
     * @param label - label of task
     */
    public SimpleTask(String label) {
        id = lastID++;
        this.label = label;
        this.status = Status.NOT_STARTED;
    }


    /**
     * Creates/replace existing attribure of task with value
     * @param key attribute name
     * @param value new value
     */
    public void setAtribute(String key, Object value) {
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

    public String getLabel() {
        return label;
    }

    public Status getStatus() {
        return status;
    }

    public Map<String, Object> getAtriibutes() {
        return atriibutes;
    }
}
