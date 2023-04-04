package com.fourTL.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.OrderDetails;
import com.fourTL.entities.Orders;




public interface OrdersDAO extends JpaRepository<Orders, Long>{
	
	List<Orders> findByUsernameContaining(String search);
	
}
