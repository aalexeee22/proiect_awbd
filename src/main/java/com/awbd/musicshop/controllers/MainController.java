package com.awbd.musicshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {


    @RequestMapping("")
    public String productForm() {

        return "main";
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // se redă templates/register.html
    }


    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "accessDenied"; }

}