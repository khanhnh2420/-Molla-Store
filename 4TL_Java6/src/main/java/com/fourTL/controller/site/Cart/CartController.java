package com.fourTL.controller.site.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	@GetMapping("/viewCart")
	private String viewCart() {
		return "site/cart";
	}
}
