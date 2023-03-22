package com.fourTL.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.service.UserService;

@Controller
public class LoginController {
	@RequestMapping("/login/form")
	public String form() {
		return "site/login";
	}
	
	@RequestMapping("/login/success")
	public String success() {
		return "redirect:/";
	}
	
	@RequestMapping("/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/login/form";
	}
	
	@RequestMapping("/login/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		return "forward:/login/form";
	}
	
	
	// OAuth2
	
	@Autowired UserService userService;
	
	@RequestMapping("/oauth2/login/success")
	public String oauth2Success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/login/success";
	}
}
