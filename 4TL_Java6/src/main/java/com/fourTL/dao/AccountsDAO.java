package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.Accounts;

public interface AccountsDAO extends JpaRepository<Accounts, String>{

}
