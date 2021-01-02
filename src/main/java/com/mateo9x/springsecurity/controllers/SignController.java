package com.mateo9x.springsecurity.controllers;

import com.mateo9x.springsecurity.model.User;
import com.mateo9x.springsecurity.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SignController {

    UserRepository userRepository;

    public SignController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logsuccess")
    public String logSuccess(){
        return "index_logged";
    }

    @RequestMapping("/myprofile")
    public String myUserProfile(Model model,Principal principal) {
        model.addAttribute("user", userRepository.findUserByUsername(principal.getName()));
        return "myprofile";
    }


}
