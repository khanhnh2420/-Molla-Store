package com.fourTL.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fourTL.entities.OrderDetails;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails, Long>{
	
	@Query("SELECT ordt FROM OrderDetails ordt GROUP BY ordt ORDER BY COUNT(ordt) DESC LIMIT 6")
	List<OrderDetails> findTopSellingProducts();
}
