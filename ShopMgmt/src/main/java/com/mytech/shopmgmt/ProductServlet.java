package com.mytech.shopmgmt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.Product;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ProductServlet
 */
@MultipartConfig
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
			} else {
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
		
		if ("add".equals(action)) {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String priceString = request.getParameter("price");
		double price = Double.parseDouble(priceString);
		
		// Lấy file hình ảnh từ form
		Part imagePart = request.getPart("image");

		String imagePath = saveImage(imagePart); // Lưu hình ảnh và lấy đường dẫn
		Product product = new Product(code, name, price, imagePath);
		productDao.addProduct1(product);
		System.out.println("prouct" + product);
		response.sendRedirect("products");

		}else if ("update".equals(action)) {
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));

			// Lấy thông tin sản phẩm hiện tại
			Product existingProduct = productDao.getProductByCode(code);
			String oldImagePath = existingProduct.getImagePath(); // Ảnh cũ từ DB
			String imagePath = oldImagePath; // Mặc định giữ ảnh cũ

			// Lấy hình ảnh mới từ form (nếu có)
			Part imagePart = request.getPart("image");
			try {
				if (imagePart != null && imagePart.getSize() > 0) {
					// Xóa hình cũ trước khi lưu hình mới
					if (oldImagePath != null && !oldImagePath.isEmpty()) {
						deleteOldImage(oldImagePath);
					}
					imagePath = saveImage(imagePart); // Lưu ảnh mới
				}

				Product updatedProduct = new Product(code, name, price, imagePath);
				productDao.updateProduct(updatedProduct);

				response.sendRedirect("products"); // Quay lại danh sách sản phẩm
			} catch (IllegalArgumentException e) {
				// Catch the error for invalid file type or size
				request.setAttribute("errorMessage", e.getMessage());
				request.setAttribute("product", existingProduct); // Keep existing product details
				ServletHelper.forward(request, response, "productUpdate"); // Show the form again with error
			}
		} else {
			doGet(request, response);
		}
	}

	// Phương thức để lưu hình ảnh vào thư mục và trả về đường dẫn
	private String saveImage(Part imagePart) throws IOException {
		if (imagePart != null && imagePart.getSize() > 0) {
			// Kiểm tra loại tệp
			String contentType = imagePart.getContentType();
			if (contentType == null || !contentType.startsWith("image/")) {
				// Nếu không phải là hình ảnh, thông báo lỗi
				throw new IllegalArgumentException("File must be an image.");
			}

			// Kiểm tra kích thước tệp (ví dụ 5MB)
			long maxSize = 5 * 1024 * 1024; // 5MB
			if (imagePart.getSize() > maxSize) {
				throw new IllegalArgumentException("File size exceeds the maximum limit of 5MB.");
			}

			String imageName = imagePart.getSubmittedFileName();

			// thêm biến tránh trùng lặp tên
			String fileExtension = imageName.substring(imageName.lastIndexOf("."));
			String baseName = imageName.substring(0, imageName.lastIndexOf("."));

			// Lấy đường dẫn thư mục lưu trữ
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);
			// tạo thư mục mới nếu chưa có
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			// in đường dẫn
			System.out.println("Upload directory: " + uploadDir.getAbsolutePath());

			// Lưu file vào thư mục uploads
			File file = new File(uploadDir, imageName);

			// đổi tên nếu trùng
			int counter = 1;
			while (file.exists()) {
				// Nếu file đã tồn tại, thêm số vào tên file
				imageName = baseName + "_" + counter + fileExtension;
				file = new File(uploadDir, imageName);
				counter++;
			}
			String imagePath = "uploads/" + imageName; // Đường dẫn nơi lưu hình ảnh
			// Lưu tệp lên server bằng cách đọc dữ liệu từ imagePart và ghi ra file đích
			try (
					// Tạo luồng đọc dữ liệu từ file người dùng upload
					InputStream inputStream = imagePart.getInputStream();
					// Tạo luồng ghi dữ liệu ra file trên server (file là đối tượng File với đường
					// dẫn lưu trữ cụ thể)
					FileOutputStream outputStream = new FileOutputStream(file)) {

				// Tạo một bộ đệm 4KB để đọc và ghi từng phần của file (tránh chiếm nhiều bộ
				// nhớ)
				byte[] buffer = new byte[4096];
				// Biến để lưu số byte đọc được trong mỗi vòng lặp
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					// Ghi phần dữ liệu đã đọc vào file trên server
					outputStream.write(buffer, 0, bytesRead);
				}
			}

			return imagePath; // Trả về đường dẫn lưu trữ ảnh
		}
		return ""; // Nếu không có hình ảnh mới, trả về chuỗi rỗng
	}

	// xóa ảnh cũ khi update hình mới
	private void deleteOldImage(String imagePath) {
		String uploadPath = getServletContext().getRealPath("/") + imagePath; // Đường dẫn đầy đủ
		File file = new File(uploadPath);

		if (file.exists()) {
			file.delete(); // Xóa file cũ
			System.out.println("Đã xóa hình cũ: " + uploadPath);
		} else {
			System.out.println("Không tìm thấy hình cũ để xóa: " + uploadPath);
		}
	}

}
