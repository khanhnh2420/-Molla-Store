package com.fourTL.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.OrdersDAO;
import com.fourTL.entities.Accounts;
import com.fourTL.entities.Orders;
import com.fourTL.service.AcountService;
import com.fourTL.service.OrdersService;


@Service
public abstract class AcountServiceImpl implements AcountService {

	@Autowired
	AccountsDAO accountsDAO;

	@Override
	public List<Accounts> findAll() {
		return accountsDAO.findAll();
	}

	@Override
	public Accounts findById(String username) {
		return accountsDAO.findById(username).get();
	}
	
}
