package com.fourTL.controller.site.AboutusAndContact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutusAndContactController {
	@RequestMapping("/aboutus")
	public String AboutUs() {
		return "site/about";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "site/contact";
	}
}
