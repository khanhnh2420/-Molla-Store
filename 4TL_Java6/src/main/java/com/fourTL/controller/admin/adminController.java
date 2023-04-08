package com.fourTL.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminController {
	@RequestMapping("/admin/dashboard")
	public String dashboard() {
		return "admin/index";
	}
	
	@RequestMapping("/admin/product")
	public String product() {
		return "admin/product";
	}
	
	@RequestMapping("/admin/orders")
	public String orders() {
		return "admin/orders";
	}
	
	@RequestMapping("/admin/acounts")
	public String maneger() {
		return "admin/maneger";
	}
}
