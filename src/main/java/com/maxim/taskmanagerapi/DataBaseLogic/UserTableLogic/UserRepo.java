package com.maxim.taskmanagerapi.DataBaseLogic.UserTableLogic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDAO, Long> {
    public Optional<UserDAO> findById(Long id);

    public Optional<UserDAO> findByLogin(String username);

    @Modifying
    public default void insertUser(UserDAO user){
        this.saveAndFlush(user);
    }



}
