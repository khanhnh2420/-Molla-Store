package com.fourTL.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.Accounts;
import com.fourTL.entities.Orders;

public interface AccountsDAO extends JpaRepository<Accounts, String>{
	List<Accounts> findByUsernameContaining(String search);
}
