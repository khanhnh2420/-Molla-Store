package com.fourTL.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
public class OrderDetails  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long price;
	@ManyToOne
	@JoinColumn(name = "Productid")
	Products product;
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Orders order;
}
