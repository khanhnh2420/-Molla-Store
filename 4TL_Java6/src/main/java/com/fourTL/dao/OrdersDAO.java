package com.fourTL.dao;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.fourTL.entities.Orders;






public interface OrdersDAO extends JpaRepository<Orders, Long>{
	
	 @Query("SELECT o FROM Orders o WHERE o.account.username = ?1")
	 	List<Orders> findByUsernameContaining(String search);
	
}
