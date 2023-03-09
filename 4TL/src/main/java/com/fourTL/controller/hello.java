package com.fourTL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hello {
    @RequestMapping("/home")
    public String hello(Model model) {
    	System.out.println("check");
        model.addAttribute("message", "Hello, Khanh!");
        return "hello";
    }
}

