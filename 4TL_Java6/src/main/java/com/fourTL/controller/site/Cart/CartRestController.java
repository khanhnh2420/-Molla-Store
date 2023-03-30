package com.fourTL.controller.site.Cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.entities.Cart;
import com.fourTL.entities.Products;
import com.fourTL.service.ProductService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("restCart")
public class CartRestController {

	@Autowired
	ProductService pService;

	@PostMapping("/addCart/{id}")
	private HashMap<Integer, Cart> addItem(HttpSession ss, @PathVariable("id") Integer productID) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) ss.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Products products = pService.findById(productID);
		if (products != null) {
			if (cartItems.containsKey(productID)) {
				Cart item = cartItems.get(productID);
				item.setProduct(products);
				item.setQuantity(1);
				cartItems.put(productID, item);
			} else {
				Cart item = new Cart();
				item.setProduct(products);
				item.setQuantity(1);
				cartItems.put(productID, item);
			}
		}
		ss.setAttribute("myCart", cartItems);
		ss.setAttribute("myCartTotal", totalPrice(cartItems));
		ss.setAttribute("myCartLength", cartItems.size());
		return null;
	}

	@PostMapping("/removeCart/{id}")
	private HashMap<Integer, Cart> removeItem(HttpSession ss, @PathVariable("id") Integer productID) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) ss.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(productID)) {
			cartItems.remove(productID);
		}
		ss.setAttribute("myCart", cartItems);
		ss.setAttribute("myCartTotal", totalPrice(cartItems));
		ss.setAttribute("myCartLength", cartItems.size());
		return null;
	}

	@PostMapping("/clearCart")
	private HashMap<Integer, Cart> clearCart(HttpSession ss) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) ss.getAttribute("myCart");
		cartItems = new HashMap<>();
		ss.setAttribute("myCart", cartItems);
		ss.setAttribute("myCartTotal", totalPrice(cartItems));
		ss.setAttribute("myCartLength", cartItems.size());
		return null;
	}

	@GetMapping("/getCart")
	private HashMap<Integer, Cart> getCart(HttpSession ss) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) ss.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		ss.setAttribute("myCart", cartItems);
		return cartItems;
	}

	@GetMapping("/getCartTotal")
	private double getCartTotal(HttpSession ss) {
		Double cartTotalObj = (Double) ss.getAttribute("myCartTotal");
		double cartTotal = cartTotalObj != null ? cartTotalObj : 0;
		return cartTotal;
	}

	@GetMapping("/getCartLength")
	private int getCartLength(HttpSession ss) {
		Integer cartLenghtObj = (Integer) ss.getAttribute("myCartLength");
		int cartLenght = cartLenghtObj != null ? cartLenghtObj : 0;
		return cartLenght;
	}

	private double totalPrice(HashMap<Integer, Cart> cartItems) {
		int count = 0;
		for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return count;
	}
}
