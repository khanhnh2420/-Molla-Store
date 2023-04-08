package com.fourTL.service;

import java.util.List;





import com.fourTL.entities.Orders;




public interface OrdersService {
	
	List<Orders> findAll();

	Orders findById(Long id);
	

}
