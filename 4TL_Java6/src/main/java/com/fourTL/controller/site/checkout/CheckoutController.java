package com.fourTL.controller.site.checkout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
	@RequestMapping("/checkout")
	public String checkout() {
		return "site/checkout";
	}
}
