package com.fourTL.controller.site.Home;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fourTL.dao.OrderDetailsDAO;
import com.fourTL.entities.Categories;
import com.fourTL.entities.OrderDetails;
import com.fourTL.entities.Products;
import com.fourTL.service.CategoryService;
import com.fourTL.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDetailsDAO orderDetailsDAO;
	
	@Autowired
	CategoryService catesService;
	
	@GetMapping("/")
	private String index(Model model) {
		// List all product
		List<Products> listProduct = productService.findAll();
		model.addAttribute("products", getRandom(listProduct,6));
		// List all category
		List<Categories> listCategories = catesService.findAll();
		model.addAttribute("cates", listCategories);
		// List Top Selling Products
		List<OrderDetails> listProductTrending = orderDetailsDAO.findTopSellingProducts();
		model.addAttribute("productsTrending", listProductTrending);
		
		return "site/home";
	}
	
	public static <T> List<T> getRandom(List<T> list, int n) {
		// Hàm trả list sản phẩm random theo số lượng
	    List<T> randomList = new ArrayList<>();
	    Random random = new Random();
	    while (randomList.size() < n && !list.isEmpty()) {
	      int index = random.nextInt(list.size());
	      T randomElement = list.get(index);
	      if (!randomList.contains(randomElement)) {
	        randomList.add(randomElement);
	      }
	      list.remove(index);
	    }
	    return randomList;
	  }
}
