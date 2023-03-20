package com.fourTL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	private String index(Model model) {
		return "site/home";
	}
	
	@GetMapping("/login")
	private String test(Model model) {
		return "site/login";
	}
	
	@GetMapping("/category")
	private String test1(Model model) {
		return "site/category";
	}
}
