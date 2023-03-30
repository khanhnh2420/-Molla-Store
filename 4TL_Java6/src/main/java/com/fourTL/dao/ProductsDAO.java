package com.fourTL.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fourTL.entities.Products;

@Repository
public interface ProductsDAO extends JpaRepository<Products, Integer> {

	@Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
	List<Products> findByCategoryId(@Param("categoryId") String categoryId);

	//Tìm sản phẩm theo search
	List<Products> findByNameContaining(String search);
}
