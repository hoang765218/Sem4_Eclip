package com.mytech.shopmgmt.dao;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.Customer;
import com.mytech.shopmgmt.models.Product;

import jakarta.persistence.EntityManager;

public class ShopCartDao {

	public boolean addShopCart(Customer customer, Product product, int quantity) {
		EntityManager entityManager = DbConnector.getEntityManager();
		
		//1. Lay gio hang cua customer
		//2. Tao CartLine tu Product voi quantity
		//3. Them CartLine vao ShopCart
		//4. Luu ShopCart
		
		return true;
	}
}
