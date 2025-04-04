<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Add Product</h1>
		<h2>
			<a href="${pageContext.request.contextPath}${'/products'}">List
				All Products</a>
		</h2>
		<br>

		<form action="products" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="add_product" />
			<div class="mb-3">
				<label>Code:</label> <input type="text" class="form-control"
					name="code" required>
			</div>
			<div class="mb-3">
				<label>Name:</label> <input type="text" class="form-control"
					name="name" required>
			</div>
			<div class="mb-3">
				<label>Price:</label> <input type="number" step="0.01"
					class="form-control" name="price" required>
			</div>
			<div class="mb-3">
				<label>Image URL:</label> <input type="file" class="form-control"
					name="image" accept="image/*" >
			</div>
			<button type="submit" class="btn btn-success">Add Product</button>
			<a href="products" class="btn btn-secondary">Cancel</a>
		</form>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>