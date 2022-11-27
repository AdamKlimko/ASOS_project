package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class IndexController {

    @GetMapping("/")
    public String index(){ return "index"; }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/logout")
    public String logout(){ return "login"; }

    @GetMapping("/admin")
    public String admin(){ return "admin_panel"; }

    @GetMapping("/user")
    public String user(){ return "user_panel"; }

    @GetMapping("/public")
    public String for_public(){ return "public_panel"; }

    @PostMapping("/submit")
    public String postForm(String user_input, Model model){
        System.out.println(user_input);
        model.addAttribute("user_input", user_input);
        return "index";
    }
}
