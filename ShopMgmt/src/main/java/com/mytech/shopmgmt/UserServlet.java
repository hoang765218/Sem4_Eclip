package com.mytech.shopmgmt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import com.mytech.shopmgmt.dao.UserDao;
import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private static final String UPLOAD_DIR = "images"; // Thư mục lưu file

    public UserServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            response.sendRedirect("dashboard.jsp");
        } else if (action.equals("create")) {
            ServletHelper.forwardPublic(request, response, "addUser");
        } else if (action.equals("downloadImage")) {
            handleDownloadImage(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("create")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            // Xử lý upload file
            String imagePath = null;
            Part filePart = request.getPart("imagePath");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imagePath = "/" + UPLOAD_DIR + "/" + fileName;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setImagePath(imagePath);

            try {
                userDao.createUser(user);
                response.sendRedirect("login.jsp");
            } catch (SQLException e) {
                request.setAttribute("errorMessage", "Username already exists. Please choose a different username.");
                ServletHelper.forwardPublic(request, response, "addUser");
            }
        } else if (action != null && action.equals("uploadImage")) {
            handleUploadImage(request, response);
        } else {
            doGet(request, response);
        }
    }

    // Xử lý upload hình ảnh mới
    private void handleUploadImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String imagePath = null;
        Part filePart = request.getPart("imagePath");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            imagePath = "/" + UPLOAD_DIR + "/" + fileName;

            // Xóa hình cũ nếu có
            String oldImagePath = (String) session.getAttribute("imagePath");
            if (oldImagePath != null) {
                File oldFile = new File(getServletContext().getRealPath("") + File.separator + oldImagePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // Cập nhật imagePath trong database
            try {
                userDao.updateImagePath(username, imagePath);
                // Cập nhật session
                session.setAttribute("imagePath", imagePath);
                request.setAttribute("successMessage", "Image uploaded successfully!");
            } catch (SQLException e) {
                request.setAttribute("errorMessage", "Failed to update image. Please try again.");
            }
        } else {
            request.setAttribute("errorMessage", "Please select an image to upload.");
        }

        ServletHelper.forwardPublic(request, response, "profile");
    }

    // Xử lý download hình ảnh
    private void handleDownloadImage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String imagePath = (String) session.getAttribute("imagePath");

        if (imagePath == null) {
            response.sendRedirect("profile.jsp");
            return;
        }

        String filePath = getServletContext().getRealPath("") + File.separator + imagePath;
        File downloadFile = new File(filePath);

        if (!downloadFile.exists()) {
            response.sendRedirect("profile.jsp");
            return;
        }

        // Thiết lập header để tải file
        response.setContentType("application/octet-stream");
        response.setContentLength((int) downloadFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");

        // Gửi file về client
        try (FileInputStream inStream = new FileInputStream(downloadFile);
             OutputStream outStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
    }

    // Phương thức để lấy tên file từ Part
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}