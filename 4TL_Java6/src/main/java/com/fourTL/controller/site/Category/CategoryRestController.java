package com.fourTL.controller.site.Category;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.dao.ProductsDAO;
import com.fourTL.entities.Categories;
import com.fourTL.entities.CategoryDTO;

@RestController
@RequestMapping("restCategory")
public class CategoryRestController {

	@Autowired
	CategoriesDAO cDAO;

	@Autowired
	ProductsDAO pDAO;

	@GetMapping("/countProduct")
	private HashMap<String, CategoryDTO> getByCateGory() {
		HashMap<String, CategoryDTO> listCateAndCountProduct = new HashMap<>();
		
		for (Categories categories : cDAO.findAll()) {
			CategoryDTO cDTO = new CategoryDTO();
			cDTO.setNameCategory(categories.getName());
			cDTO.setQtyProduct((int) pDAO.countByCategoryId(categories.getId()));
			
			listCateAndCountProduct.put(categories.getId(), cDTO);
		}
		return listCateAndCountProduct;
	}
}
