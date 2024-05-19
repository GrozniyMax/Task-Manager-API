package com.maxim.taskmanagerapi.Entities.Users;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Component
public class UserAdapter {
    @Autowired
    private UserRepo userRepository;


    public UserDTO toUserDTO(UserDAO user) {
        return new UserDTO(user.getLogin(),
                user.getPassword());
    }

    public UserDAO toUserDAO(UserDTO userDTO) {
        var user = new UserDAO();
        user.setLogin(userDTO.login());
        user.setPassword(userDTO.password());
        return user;
    }

    public UserDTO toUserDTO(Token token) {
        return toUserDTO(userRepository.findById(token.id()).orElseThrow(NoSuchElementException::new));
    }


}
