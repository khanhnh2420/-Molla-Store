package com.fourTL.controller.site.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("category")
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
			cDTO.setIdCategory(categories.getId().trim());
			cDTO.setNameCategory(categories.getName());
			cDTO.setQtyProduct((int) pDAO.countByCategoryId(categories.getId()));

			listCateAndCountProduct.put(categories.getId(), cDTO);
		}
		return ResponseEntity.ok(listCateAndCountProduct);
	}
	
	@RequestMapping("/getAll")
	public ResponseEntity<Page<Products>> getAllProducts(
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(9));
		Page<Products> products = pDAO.findAll(pageable);
	    return ResponseEntity.ok(products);
	}

	@RequestMapping("/findByCategoryId/{categoryId}")
	public ResponseEntity<Page<Products>> viewProductsByCategoryId(
			@PathVariable("categoryId") String categoryId,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(9));
		Page<Products> products;
		if (categoryId == null || categoryId.isBlank()) {
			products = pDAO.findAll(pageable);
		} else {
			products = pDAO.findByCategoryId(categoryId, pageable);
		}
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping("/findBySource/{source}")
	public ResponseEntity<Page<Products>> viewProductsBySource(
			@PathVariable("source") String source,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(9));
		Page<Products> products;
		if (source == null || source.isBlank()) {
			products = pDAO.findAll(pageable);
		} else {
			products = pDAO.findBySource(source, pageable);
		}
		return ResponseEntity.ok(products);
	}

}
