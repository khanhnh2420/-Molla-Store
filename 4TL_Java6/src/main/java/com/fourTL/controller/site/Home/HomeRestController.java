package com.fourTL.controller.site.Home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.entities.Categories;
import com.fourTL.service.CategoryService;

@CrossOrigin("*")
@RestController
public class HomeRestController {
	
	@Autowired
	CategoryService catesService;
	
	@GetMapping("/rest/categories")
	public List<Categories> getAll(Model model) {
		return catesService.findAll();
	}
}
