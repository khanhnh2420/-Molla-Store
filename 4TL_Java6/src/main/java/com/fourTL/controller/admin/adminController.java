package com.fourTL.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.dao.ProductsDAO;

@Controller
public class adminController {
	@Autowired
	ProductsDAO productsDAO;
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	AccountsDAO accountsDAO;
	
	@Autowired
	CategoriesDAO categoriesDAO;
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
