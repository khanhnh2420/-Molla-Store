package com.fourTL.controller.site.MyAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		if (!accountsDAO.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		Accounts user = accountsDAO.findById(username).get();
		user.getOrders().sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("{username}")
	public ResponseEntity<Accounts> put(@PathVariable("username") String username, @RequestBody Accounts account) {
		if(!accountsDAO.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		accountsDAO.save(account);
		return ResponseEntity.ok(account);
	}
}
