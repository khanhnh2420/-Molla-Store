package com.fourTL.controller.site.Category;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.ProductsDAO;
import com.fourTL.entities.Categories;
import com.fourTL.entities.CategoryDTO;
import com.fourTL.entities.Products;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("restShop")
public class CategoryRestController {

	@Autowired
	CategoriesDAO cDAO;

	@Autowired
	ProductsDAO pDAO;

	@GetMapping("/countProduct")
	private ResponseEntity<HashMap<String, CategoryDTO>> getByCateGory() {
		HashMap<String, CategoryDTO> listCateAndCountProduct = new HashMap<>();

		for (Categories categories : cDAO.findAll()) {
			CategoryDTO cDTO = new CategoryDTO();
			cDTO.setNameCategory(categories.getName());
			cDTO.setQtyProduct((int) pDAO.countByCategoryId(categories.getId()));

			listCateAndCountProduct.put(categories.getId(), cDTO);
		}
		return ResponseEntity.ok(listCateAndCountProduct);
	}

	@RequestMapping("/getAll")
	public ResponseEntity<Page<Products>> viewProductsInShop(@RequestParam("page") Optional<Integer> page, Model model) throws ServletException, IOException {
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		Page<Products> pages = pDAO.findAll(pageable);
		return ResponseEntity.ok(pages);
	}
	
	@RequestMapping("/category/{categoryID}")
	public ResponseEntity<Page<Products>> viewProductsByCategoryId(@PathVariable("categoryID") String id,
			@RequestParam("page") Optional<Integer> page, Model model) throws ServletException, IOException {
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		Page<Products> pages = pDAO.findByCategoryId(id, pageable);
		if (pages.isEmpty()) {
			pages = pDAO.findAll(pageable);
		}
		return ResponseEntity.ok(pages);
	}

}
