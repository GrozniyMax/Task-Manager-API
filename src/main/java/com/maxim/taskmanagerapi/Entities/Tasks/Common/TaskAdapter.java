package com.maxim.taskmanagerapi.Entities.Tasks.Common;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAOBuilder;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTOBuilder;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserAdapter;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskAdapter {

    @Autowired
    private static UserAdapter adapter;

    public static TaskDTO toDTO(TaskDAO dao){
        return new TaskDTOBuilder().setLabel(dao.getLabel()).setDescription(dao.getDescription()).setStatus(dao.getStatus())
                .setStartTime(dao.getStart()).setDeadline(dao.getDeadline())
                .setAttributes(dao.getAttributes())
                .setSubtask(dao.getSubTasks().stream().map(TaskAdapter::toDTO).toList())
                .build();
    }

    public static TaskDAO toDAO(TaskDTO dto, UserDTO owner){
        var r = new TaskDAOBuilder().setLabel(dto.getLabel()).setDescription(dto.getDescription()).setStatus(dto.getStatus())
                .setStartTime(dto.getStartTime()).setDeadline(dto.getDeadline()).setAttributes(dto.getAttributes())
                .build();
        r.setOwnerId(adapter.toUserDAO(owner));
        return r;
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
