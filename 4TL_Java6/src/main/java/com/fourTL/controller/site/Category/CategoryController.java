package com.fourTL.controller.site.Category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
	
	@GetMapping("/category")
	private String viewCategory() {
		return "site/category";
	}
	
	@GetMapping("/category-2cols")
	private String viewCategory2Col() {
		return "site/category-2cols";
	}
	@GetMapping("/category-4cols")
	private String viewCategory4Col() {
		return "site/category-4cols";
	}
	@GetMapping("/category-list")
	private String viewCategoryList() {
		return "site/category-list";
	}
}
