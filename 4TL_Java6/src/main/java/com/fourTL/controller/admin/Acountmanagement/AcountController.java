package com.fourTL.controller.admin.Acountmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.CategoriesDAO;

import com.fourTL.entities.Accounts;
import com.fourTL.entities.Categories;



@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/acount")
public class AcountController {
	@Autowired
	AccountsDAO accountsDAO;

	@Autowired
	CategoriesDAO categoriesDAO;
	
	@GetMapping("")
	public ResponseEntity<List<Accounts>> getAll(Model model) {
		return ResponseEntity.ok(accountsDAO.findAll());
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Categories>> getAllCategories(Model model) {
		return ResponseEntity.ok(categoriesDAO.findAll());
	}
	
	@GetMapping("/search/{search}")
	public ResponseEntity<List<Accounts>> search(Model model, @PathVariable("search") String search) {
		return ResponseEntity.ok(accountsDAO.findByUsernameContaining(search));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Accounts> getOne(@PathVariable("id") String username) {
		if(!accountsDAO.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(accountsDAO.findById(username).get());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String username) {
		if(!accountsDAO.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		accountsDAO.deleteById(username);
		return ResponseEntity.ok().build();
	}
}
