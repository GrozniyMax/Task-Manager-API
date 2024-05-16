package com.maxim.taskmanagerapi.Entities;

import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAOBuilder;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTOBuilder;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;

import java.util.List;
import java.util.Map;

public class TaskAdapter {

    public static TaskDTO toDTO(TaskDAO dao){
        return new TaskDTOBuilder().setLabel(dao.getLabel()).setDescription(dao.getDescription()).setStatus(dao.getStatus())
                .setStartTime(dao.getStart()).setDeadline(dao.getDeadline())
                .setAttributes(dao.getAttributes())
                .setSubtask(dao.getSubTasks().stream().map(TaskAdapter::toDTO).toList())
                .build();
    }

    public static TaskDAO toDAO(TaskDTO dto){
        return new TaskDAOBuilder().setLabel(dto.getLabel()).setDescription(dto.getDescription()).setStatus(dto.getStatus())
                .setStartTime(dto.getStartTime()).setDeadline(dto.getDeadline()).setAttributes(dto.getAttributes())
                .build();
    }

    public static List<AttributeDAO> toAttributeDAO(Map<String,String> attributes){

        return attributes.entrySet().stream().map((attributeEntry)->{
            var r = new AttributeDAO();
            r.setKey(attributeEntry.getKey());
            r.setValue(attributeEntry.getValue());
            return r;
        }).toList();
    }
}
