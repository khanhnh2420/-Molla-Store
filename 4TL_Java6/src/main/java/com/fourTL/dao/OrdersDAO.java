package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.Orders;

public interface OrdersDAO extends JpaRepository<Orders, Long>{

}
