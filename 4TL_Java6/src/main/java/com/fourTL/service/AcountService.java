package com.fourTL.service;

import java.util.List;

import com.fourTL.entities.Accounts;



public interface AcountService {

	List<Accounts> findAll();

	Accounts findById(String username);
}
