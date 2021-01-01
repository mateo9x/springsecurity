package com.mateo9x.springsecurity.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationForm {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(id,username, firstname, lastname, email, passwordEncoder.encode(password));
    }
}
