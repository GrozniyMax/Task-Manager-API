package com.maxim.taskmanagerapi.Controllers;

import com.maxim.taskmanagerapi.DataBaseLogic.UserTableLogic.UserDAO;
import com.maxim.taskmanagerapi.DataBaseLogic.UserTableLogic.UserRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is responsible for operations
 * <ul>user login</ul>
 * <ul>user password change</ul>
 */

@RestController("/")
public class AuthentificationController {

    @Autowired
    UserRepo userRepository;

    @GetMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) throws BadRequestException {
        String dataBasePassword = userRepository.findByLogin(username).orElseThrow(()->new BadRequestException("Wrong login")).getPassword();
        if (!dataBasePassword.equals(password)) throw new BadRequestException("Wrong password");
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Wrong password")
    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(BadRequestException e) {
        // do nothing
    }

    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password) throws BadRequestException {
        try {
            userRepository.insertUser(new UserDAO(username,password));
        } catch (Exception e) {
            throw new BadRequestException("Invalid login or password");
        }
    }


}
