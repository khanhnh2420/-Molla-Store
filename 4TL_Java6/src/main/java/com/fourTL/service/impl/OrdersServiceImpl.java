package com.fourTL.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.Orders;
import com.fourTL.service.OrdersService;


@Service
public abstract class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDAO ordersdao;

	@Override
	public List<Orders> findAll() {
		return ordersdao.findAll();
	}

	@Override
	public Orders findById(Long id) {
		return ordersdao.findById(id).get();
	}
	
}
