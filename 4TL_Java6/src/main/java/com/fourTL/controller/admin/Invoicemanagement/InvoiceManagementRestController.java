package com.fourTL.controller.admin.Invoicemanagement;

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

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.Categories;
import com.fourTL.entities.Orders;


@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/order")
public class InvoiceManagementRestController {
	@Autowired
	OrdersDAO ordersdao;

	@Autowired
	CategoriesDAO categoriesDAO;

	@GetMapping("")
	public ResponseEntity<List<Orders>> getAll(Model model) {
		return ResponseEntity.ok(ordersdao.findAll());
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Categories>> getAllCategories(Model model) {
		return ResponseEntity.ok(categoriesDAO.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Orders> getOne(@PathVariable("id") Long id) {
		if(!ordersdao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ordersdao.findById(id).get());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(!ordersdao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ordersdao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
