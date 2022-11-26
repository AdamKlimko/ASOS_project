package com.example.demo.controllers;

import com.example.demo.model.UserInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class IndexController {

    @GetMapping("/")
    public String index(UserInput user_input){ return "index"; }

    @GetMapping("/admin")
    public String admin(){ return "admin_panel"; }

    @GetMapping("/user")
    public String user(){ return "user_panel"; }

    @GetMapping("/public")
    public String for_public(){ return "public_panel"; }

    @PostMapping("/submit")
    public String postForm(UserInput user_input){
        System.out.println(user_input.getInput());
        return "index";
    }
}
