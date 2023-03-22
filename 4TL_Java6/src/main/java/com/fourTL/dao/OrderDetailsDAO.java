package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.OrderDetails;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails, Long>{

}
