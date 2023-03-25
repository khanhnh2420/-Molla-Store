package com.fourTL.controller.site.Orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.OrderDetails;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {
	@Autowired
	OrdersDAO ordersDAO;

	@GetMapping("{orderId}")
	public ResponseEntity<List<OrderDetails>> getListOrderDetails(@PathVariable("orderId") Long orderId) {
		if (!ordersDAO.existsById(orderId)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ordersDAO.findById(orderId).get().getOrderDetails());
	}
}
