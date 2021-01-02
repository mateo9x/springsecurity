package com.mateo9x.springsecurity.controllers;

import com.mateo9x.springsecurity.model.RegistrationForm;
import com.mateo9x.springsecurity.model.User;
import com.mateo9x.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public String registerForm(User user, Model model) {
        model.addAttribute("user", user);
        return "register";
    }

   @PostMapping
    public String registrationProcess(@Valid @ModelAttribute ("user") RegistrationForm registrationForm,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userRepository.save(registrationForm.toUser(passwordEncoder));
            return "redirect:/login";
        }
    }

  /*  @PostMapping
    public String registrationProcess(@Valid @ModelAttribute ("user") RegistrationForm registrationForm,User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        else {

            User isEmailExist = userRepository.findUserByEmail(user.getEmail());
            if(isEmailExist != null) {
                System.out.println("User with this email already exist!");
                return "register";
            }
                userRepository.save(registrationForm.toUser(passwordEncoder));
                return "redirect:/login";

        }
    } */
}
