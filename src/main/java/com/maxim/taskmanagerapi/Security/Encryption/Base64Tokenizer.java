package com.maxim.taskmanagerapi.Security.Encryption;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Entities.Users.UserAdapter;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import com.maxim.taskmanagerapi.Security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

@Component
@EnableScheduling
public class Base64Tokenizer implements Tokenizer {

    private static final HashMap<Character, Character> baseHashMap;

    static {
        baseHashMap = new HashMap<>();
        IntStream.range(0, 15).mapToObj((i) -> Character.valueOf(Integer.toString(i, 16).charAt(0))).forEach(i -> baseHashMap.put(i, i));
        baseHashMap.put('_', '_');
    }

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserAdapter adapter;

    private HashMap<Character, Character> previous = new HashMap<>();

    private HashMap<Character, Character> current = new HashMap<>();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public Base64Tokenizer() {
        change();
    }


    public void change() {
        previous.clear();
        previous.putAll(current);
        current.clear();
        ArrayList<Character> chars = new ArrayList<>();
        IntStream.range(0, 15).mapToObj((i) -> Character.valueOf(Integer.toString(i, 16).charAt(0))).forEach(i -> chars.add(i));
        chars.add('_');
        for (char c = 'A'; c <= 'Z'; c++) {
            chars.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            chars.add(c);
        }
        Random r = new Random();
        for (Character c : baseHashMap.keySet()) {
            int index = r.nextInt(chars.size());
            current.put(c, chars.get(index));
            chars.remove(index);
        }
    }


    @Override
    public String tokenize(Token token) {
        char[] chars = (token.id() + "_" + formatter.format(token.createdAt())).toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            stringBuilder.append(current.get(c));
        }
        return Base64.getEncoder().encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     *User modifies token by adding first 2 letters of his/her password hash to the end of token
     */
    @Override
    public Token detokenize(String token) throws AccessException {
        String userPassword = token.substring(token.length()-2);
        token = token.substring(0, token.length()-2);
        byte[] decoded = Base64.getDecoder().decode(token);
        String decodedString = new String(decoded, StandardCharsets.UTF_8);
        String finalTokenString;
        try {
            finalTokenString = parseTokenWithCurrent(decodedString);
        }catch (ParseException e) {
            try {
                finalTokenString = parseTokenWithPrevious(decodedString);
            } catch (ParseException ex) {
                throw new AccessException("Wrong token");
            }
        }
        String[] split = finalTokenString.split("_");
        Long id = Long.parseLong(split[0]);
        if (!userRepository.findById(id).orElseThrow(() -> new AccessException("User does not exist"))
                .getPassword().startsWith(userPassword)) throw new AccessException("Wrong user letters");
        LocalDateTime createdAt = LocalDateTime.parse(split[1], formatter);
        return new Token(id, createdAt);
    }

    @Override
    public UserDTO detokenizeToDTO(String token) throws AccessException {
        return adapter.toUserDTO(detokenize(token));
    }

    private String parseTokenWithCurrent(String token) throws ParseException {
        try {
            char[] chars = token.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                chars[i] = current.get(chars[i]);
            }
            return new String(chars);
        } catch (Exception e) {
            throw new ParseException("Failed to parse using current",0);
        }
    }

    private String parseTokenWithPrevious(String token) throws ParseException {
        try {
            char[] chars = token.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                chars[i] = previous.get(chars[i]);
            }
            return new String(chars);
        } catch (Exception e) {
            throw new ParseException("Failed to parse using previous",0);
        }
    }
}
