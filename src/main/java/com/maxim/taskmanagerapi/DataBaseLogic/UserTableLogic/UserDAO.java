package com.maxim.taskmanagerapi.DataBaseLogic.UserTableLogic;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Basic class for user from table users
 */

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;


    public UserDAO(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }


}
