package com.fourTL.controller.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.ProductsDAO;
import com.fourTL.dao.RolesDAO;
import com.fourTL.entities.Categories;
import com.fourTL.entities.Products;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/product")
public class ProductRestController {
	@Autowired
	ProductsDAO productsDAO;
	
	@Autowired
	RolesDAO rolesDAO;
	
	@Autowired
	CategoriesDAO categoriesDAO;
	
	@GetMapping("")
	public ResponseEntity<List<Products>> getAll(Model model) {
		return ResponseEntity.ok(productsDAO.findAll());
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Categories>> getAllCategories(Model model) {
		return ResponseEntity.ok(categoriesDAO.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Products> getOne(@PathVariable("id") Integer id) {
		if(!productsDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productsDAO.findById(id).get());
	}
	
	@PostMapping("")
	public ResponseEntity<Products> post(@RequestBody Products product) {
		if(productsDAO.existsById(product.getId())) {
			return ResponseEntity.badRequest().build();
		}
		productsDAO.save(product);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Products> put(@PathVariable("id") Integer id, @RequestBody Products product) {
		if(!productsDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productsDAO.save(product);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if(!productsDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productsDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
