package com.fourTL.controller.site.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.dao.ProductsDAO;
import com.fourTL.entities.Products;
import com.fourTL.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService pService;
	
	@Autowired
	ProductsDAO pDAO;
	
	@RequestMapping("/product/detail/{id}")
	private String index(Model model, @PathVariable("id") Integer id) {
		// Lấy sản phẩm theo ID
		Products item = pService.findById(id);
		model.addAttribute("item", item);
		// Xử lí chuỗi hình ảnh thành từng hình
		String[] itemIMG = item.getThumbnail().split("-\\*-");
		model.addAttribute("itemIMG", itemIMG);
		// Lấy danh sách sản phẩm cùng loại
		List<Products> sameProduct = pDAO.findByCategoryId(item.getCategory().getId());
		model.addAttribute("sameProduct", sameProduct);
		return "site/product-detail";
	}
}
