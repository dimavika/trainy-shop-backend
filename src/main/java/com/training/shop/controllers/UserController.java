package com.training.shop.controllers;

import com.training.shop.entity.Role;
import com.training.shop.entity.User;
import com.training.shop.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationGet(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   Model model){
        User userFromDB = userService.findByName(username);

        if (userFromDB != null){
            return "/registration";
        }

        User user = new User(username, password, Collections.singleton(Role.USER));
        userService.create(user);

        return "redirect:/home";
    }
}
