package com.fourTL.controller.admin;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.ProductsDAO;
import com.fourTL.entities.Categories;

@Controller
public class adminController {
	@Autowired
	ProductsDAO productsDAO;
	
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
}
