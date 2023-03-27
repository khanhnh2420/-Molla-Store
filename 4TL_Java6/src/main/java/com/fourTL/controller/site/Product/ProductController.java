package com.fourTL.controller.site.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.entities.Products;
import com.fourTL.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/detail/{id}")
	private String index(Model model, @PathVariable("id") Integer id) {
		Products item = productService.findById(id);
		model.addAttribute("item", item);
		String[] itemIMG = item.getThumbnail().split("-\\*-");
		model.addAttribute("itemIMG", itemIMG);
		return "site/product-detail";
	}
}
