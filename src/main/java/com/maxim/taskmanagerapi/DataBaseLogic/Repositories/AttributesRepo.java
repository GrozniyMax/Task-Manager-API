package com.maxim.taskmanagerapi.DataBaseLogic.Repositories;

import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributesRepo extends JpaRepository<AttributeDAO, Long> {

    public AttributeDAO findById(long id);

}
