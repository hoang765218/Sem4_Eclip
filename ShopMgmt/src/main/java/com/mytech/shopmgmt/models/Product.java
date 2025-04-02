package com.mytech.shopmgmt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity(name="Product")
@Table(name= "Products")
@NamedQuery(name = "Product.findByCode",query ="SELECT p FROM Product p WHERE p.code = :code")
@NamedQuery(name = "Product.findName",query ="SELECT p FROM Product p WHERE p.name like :name")
@NamedQuery(name = "Product.findall",query ="SELECT p FROM Product p")
//@NamedQuery(name = "Product.findall",query ="SELECT e FROM Ten Entity alias e")
public class Product {
	
	@Id
	private String code;
	
	@Column(name = "name", nullable = false, length = 256, unique = true)
	private String name;
	
	
	private double price;
	private String imagePath;

	public Product() {
		super();
		
	}

	public Product(String code, String name, double price, String imagePath) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + ", imagePath=" + imagePath + "]";
	}

}
