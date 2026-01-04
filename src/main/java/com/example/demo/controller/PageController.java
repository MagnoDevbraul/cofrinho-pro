package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "forward:/cadastro.html";
    }

    @GetMapping("/index")
    public String index() {
        return "forward:/index.html";
    }
}