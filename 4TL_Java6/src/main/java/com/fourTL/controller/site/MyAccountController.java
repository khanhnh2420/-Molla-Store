package com.fourTL.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.Accounts;

@Controller
public class MyAccountController {
	@Autowired
	AccountsDAO accountsDAO;
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@RequestMapping("/account")
	public String account(@RequestParam("username") String username, Model model) {
		Accounts user = accountsDAO.findById(username).get();
		user.getOrders().sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
		model.addAttribute("user", user);
		return "site/dashboard";
	}
}
