package com.fourTL.controller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.AuthoritiesDAO;
import com.fourTL.entities.Authorities;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class CategoryRestController {
	@Autowired
	AuthoritiesDAO dao;
	
	@GetMapping("")
	public ResponseEntity<List<Authorities>> getAll(Model model) {
		return ResponseEntity.ok(dao.findAll());
	}
	
//	@GetMapping("{id}")
//	public ResponseEntity<Accounts> getOne(@PathVariable("id") String id) {
//		if(!dao.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(dao.findById(id).get());
//	}
	
//	@PostMapping("")
//	public ResponseEntity<Category> post(@RequestBody Category category) {
//		if(dao.existsById(category.getId())) {
//			return ResponseEntity.badRequest().build();
//		}
//		dao.save(category);
//		return ResponseEntity.ok(category);
//	}
//	
//	@PutMapping("{id}")
//	public ResponseEntity<Category> put(@PathVariable("id") String id, @RequestBody Category category) {
//		if(!dao.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		dao.save(category);
//		return ResponseEntity.ok(category);
//	}
//	
//	@DeleteMapping("{id}")
//	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
//		if(!dao.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		dao.deleteById(id);
//		return ResponseEntity.ok().build();
//	}
}
