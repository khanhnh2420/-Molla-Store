package com.fourTL.controller.site.Category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
	
	@GetMapping("/category")
	private String viewCategory() {
		return "site/category";
	}
}
