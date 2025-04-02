<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Products</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">MyShop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="products.jsp">Products</a></li>
					<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
					<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<h2 class="text-center">Our Products</h2>

		<div class="d-flex justify-content-between mb-3">
			<input type="text" id="searchBox" class="form-control w-50"
				placeholder="Search products..."> <a href="products?action=add"
				class="btn btn-primary">Add Product</a>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Price</th>
					<th>Image</th>
					<th>Cart</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<!-- //Cach 1: JSP Scriplet de hien thi listProducts
					//Cach 2: Su dung taglib JSTL de hien listProducts -->
				<!-- Product rows will be dynamically loaded here -->

				<c:forEach items="${listProducts}" var="product">
					<tr>
						<td>${product.code}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>                
                            <img src="${pageContext.request.contextPath}${product.imagePath}" 
                                 alt="${product.name}" 
                                 style="width: 50px;" 
                                 onerror="this.src='${pageContext.request.contextPath}/images/cf_1.jpg';">
						<td><a href="products?action=addCart&code=${product.code}"
							class="btn btn-sm btn-success">Add</a></td>
						<td><a href="products?action=update&code=${product.code}"
							class="btn btn-sm btn-warning">Edit</a></td>
						<td><a href="products?action=delete&code=${product.code}"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>