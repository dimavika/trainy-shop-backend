package com.training.shop.controllers;

import com.training.shop.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3001/*")
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String home(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("user", user);
        return "home";
    }

}
