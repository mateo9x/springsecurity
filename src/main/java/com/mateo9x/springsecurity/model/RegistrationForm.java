package com.mateo9x.springsecurity.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    private Long id;
    @NotEmpty(message = "Username cant be empty!")
    @Size(min=4,max=30, message = "Chars beetween 4 and 30")
    private String username;
    @NotEmpty(message = "Firstname cant be empty!")
    private String firstname;
    @NotEmpty(message = "Lastname cant be empty!")
    private String lastname;
    @NotEmpty(message = "Email cant be empty!")
    @Email
    private String email;
    @NotEmpty(message = "Password cant be empty!")
    @Size(min=6, message = "Password too weak! Minimum 6 chars!")
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(id,username, firstname, lastname, email, passwordEncoder.encode(password));
    }
}
