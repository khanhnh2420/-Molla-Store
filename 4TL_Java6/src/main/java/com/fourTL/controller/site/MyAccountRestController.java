package com.fourTL.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.OrderDetailsDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.Accounts;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class MyAccountRestController {
	@Autowired
	AccountsDAO accountsDAO;
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	OrderDetailsDAO orderDetailsDAO;
	
	@GetMapping("{username}")
	public ResponseEntity<Accounts> getOne(@PathVariable("username") String username) {
		if(!accountsDAO.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(accountsDAO.findById(username).get());
	}
}
