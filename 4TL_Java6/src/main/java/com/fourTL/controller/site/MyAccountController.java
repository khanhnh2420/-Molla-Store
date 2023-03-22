package com.fourTL.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {
	@GetMapping("/account")
	public String MyAccount() {
		return "site/dashboard";
	}
}
