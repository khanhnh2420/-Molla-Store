package com.fourTL.controller.site.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {

	@RequestMapping("")
	private String add(ModelMap mm, HttpSession ss, @PathVariable("productID") int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
