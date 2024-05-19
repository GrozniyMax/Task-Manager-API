package com.maxim.taskmanagerapi.Security.Encryption;

import com.maxim.taskmanagerapi.Entities.Users.UserAdapter;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import com.maxim.taskmanagerapi.Security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface Tokenizer {

    public String tokenize(Token token);

    public Token detokenize(String token) throws AccessException;

    public  UserDTO detokenizeToDTO(String token) throws AccessException;


}
