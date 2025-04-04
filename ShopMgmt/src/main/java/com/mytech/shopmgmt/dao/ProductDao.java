package com.mytech.shopmgmt.dao;

import java.util.List;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProductDao {
	public List<Product> getProducts(){
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findall", Product.class);
		return query.getResultList();
	}
	
	public Product getProductByCode(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByCode",Product.class);
		query.setParameter("code", code);
		return (Product) query.getSingleResult();
	}
	public Product getProductByName(String name) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByName",Product.class);
		query.setParameter("name", "%" + name + "%");
		return (Product) query.getSingleResult();
	}
	public boolean addProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		var trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(product);
		trans.commit();
		return true;
	}
	public void addProduct1(Product newProduct) {
		EntityManager entityManager = DbConnector.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			// Thêm sản phẩm mới vào cơ sở dữ liệu
			entityManager.persist(newProduct);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	public boolean updateProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		var trans = entityManager.getTransaction();
		trans.begin();
		
		Product  updProduct = entityManager.find(Product.class, product.getCode());
		
		if(updProduct != null) {
		updProduct.setName(product.getName());
		updProduct.setPrice(product.getPrice());
		updProduct.setImagePath(product.getImagePath());
		entityManager.merge(updProduct);
		}
		trans.commit();
		return true;
	}
	
	public boolean deleteProductByCode(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Product  delProduct = entityManager.find(Product.class, code);
		entityManager.remove(delProduct);
		return true;
	}
	
}
