package com.mytech.shopmgmt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.dao.ProductJDBCDao;
import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private ProductJDBCDao productDao;
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("action:" + action);

		if ("add".equals(action)) {

			ServletHelper.forward(request, response, "add_product");
		} else if ("update".equals(action)) {
			String code = request.getParameter("code");
			Product product = productDao.getProductByCode(code);
			if (product != null) {
				request.setAttribute("product", product);
				ServletHelper.forward(request, response, "edit_product");
			}else {
				ServletHelper.forward(request, response, "errors");
			}

		} else {
			List<Product> listProducts = productDao.getProducts();
			request.setAttribute("listProducts", listProducts);
			// Su dung taglib JSTL de hien listProducts
			for (Product product : listProducts) {
				System.out.println(product.toString());
			}
			ServletHelper.forward(request, response, "products");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String priceString = request.getParameter("price");
		double price = Double.parseDouble(priceString);

		Product product = new Product(code, name, price, "");
		
		if("update".equals(action)) {
			productDao.updateProduct(product);
		}else {
			productDao.addProduct(product);
		}
		
		ServletHelper.redirect(request, response, "products");

	}

}
