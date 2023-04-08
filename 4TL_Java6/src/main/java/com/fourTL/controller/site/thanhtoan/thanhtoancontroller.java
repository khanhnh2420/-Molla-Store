package com.fourTL.controller.site.thanhtoan;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.OrderDetailsDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.OrderDetails;
import com.fourTL.entities.Orders;

@RestController
@RequestMapping("/api")
public class thanhtoancontroller {

	@Autowired
	OrdersDAO ordersdao;

	@Autowired
	OrderDetailsDAO orderDetailsDAO;

	@GetMapping("/vieworders")
	public ResponseEntity<List<Orders>> getAll(Model model) {
		return ResponseEntity.ok(ordersdao.findAll());
	}
	
	@GetMapping("/vieworderdetail")
	public ResponseEntity<List<OrderDetails>> getAll1(Model model) {
		return ResponseEntity.ok(orderDetailsDAO.findAll());
	}

	@PostMapping("/createorders")
	public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
		ordersdao.save(orders);
		return ResponseEntity.ok(orders);
	}
	
	@PostMapping("/createorderdetail")
	public ResponseEntity<OrderDetails> createOrder(@RequestBody OrderDetails orderDetails) {
		orderDetailsDAO.save(orderDetails);
		return ResponseEntity.ok(orderDetails);
	}
	
//	@PostMapping("/createorders")
//    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
//        order = ordersdao.save(order);
//        for (OrderDetails detail : order.getOrderDetails()) {
//            detail.setOrder(order);
//        }
//        ordersdao.save(order);
//        return ResponseEntity.ok(order);
//    }

}
