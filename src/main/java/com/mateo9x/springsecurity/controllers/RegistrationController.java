package com.mateo9x.springsecurity.controllers;

import com.mateo9x.springsecurity.model.RegistrationForm;
import com.mateo9x.springsecurity.model.User;
import com.mateo9x.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model, User user){

        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping
    public String registrationProcess(RegistrationForm registrationForm){
        userRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
