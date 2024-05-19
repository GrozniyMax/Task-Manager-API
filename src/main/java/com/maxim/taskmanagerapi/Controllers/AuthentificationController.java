package com.maxim.taskmanagerapi.Controllers;

import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Security.Encryption.Tokenizer;
import com.maxim.taskmanagerapi.Security.Token;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * This controller is responsible for operations
 * <ul>user login</ul>
 * <ul>user password change</ul>
 */

@RestController("/")
public class AuthentificationController {

    @Autowired
    UserRepo userRepository;

    @Autowired
    Tokenizer tokenizer;

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws BadRequestException {
        var user = userRepository.findByLogin(username).orElseThrow(()->new BadRequestException("Wrong login"));
        String dataBasePassword = user.getPassword();
        if (!dataBasePassword.equals(password)) throw new BadRequestException("Wrong password");
        var token = new Token(user.getId(),
                LocalDateTime.now().plusHours(2));
        return tokenizer.tokenize(token);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Wrong password")
    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(BadRequestException e) {
        // do nothing
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) throws BadRequestException {
        try {
            userRepository.insertUser(new UserDAO(username,password));
            var user = userRepository.findByLogin(username).orElseThrow(()->new BadRequestException("Wrong login"));
            var token = new Token(user.getId(),
                    LocalDateTime.now().plusHours(2));
            return tokenizer.tokenize(token);
        } catch (Exception e) {
            throw new BadRequestException("Invalid login or password");
        }
    }


}
