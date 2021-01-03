package com.mateo9x.springsecurity.controllers;

import com.mateo9x.springsecurity.model.RegistrationForm;
import com.mateo9x.springsecurity.model.User;
import com.mateo9x.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SignController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public SignController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logsuccess")
    public String logSuccess(HttpSession session, Principal principal){
        session.setAttribute("sessionAttribute", principal.getName());
        return "index_logged";
    }

    @RequestMapping("/myprofile")
    public String myUserProfile(Model model,Principal principal) {
        model.addAttribute("user", userRepository.findUserByUsername(principal.getName()));
        return "myprofile";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model,Principal principal){
        model.addAttribute("user", userRepository.findUserByUsername(principal.getName()));

        return "passwordChange";
    }
    @PostMapping("/passwordChange")
    public String passwordChangeProcess(@Valid @ModelAttribute ("user") RegistrationForm registrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "passwordChange";
        } else {
            userRepository.save(registrationForm.toUser(passwordEncoder));
            return "myprofile";
        }
    }


}
