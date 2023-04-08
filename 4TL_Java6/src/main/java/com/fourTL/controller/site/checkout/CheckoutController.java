package com.fourTL.controller.site.checkout;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourTL.dao.OrderDetailsDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.OrderDetails;
import com.fourTL.entities.Orders;






@Controller
public class CheckoutController {
	
	@Autowired
    private OrdersDAO ordersDAO;
	
	@Autowired
    private OrderDetailsDAO detailsDAO;
	
	@RequestMapping("/checkout")
	public String checkout() {
		return "site/checkout";
	}
	

	@PostMapping("/createorders")
    public String addCustomer(@ModelAttribute("customer") OrderDetails details, Orders orders, Model model) {
        ordersDAO.save(orders);
        detailsDAO.save(details);
        model.addAttribute("message", "Customer added successfully");
        return "site/checkout";
    }
}
