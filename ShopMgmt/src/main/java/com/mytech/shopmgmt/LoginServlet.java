package com.mytech.shopmgmt;

import java.io.IOException;
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.mytech.shopmgmt.db.DbConnector;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Login page initializing.....");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Login page destroying.....");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.append("You Login with:" + username + " :: " + password);

		// kiem tra username = admin & password = 123456
		// Neu dung thi chuyen qua trang dashboard
		// neu sai thì chuyển về trang login.jsp

		if ("admin".equals(username) && "123456".equals(password)) {
//				 RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
//				 	requestDispatcher.forward(request, response);
			Cookie ckUsername = new Cookie("username", username);
			Cookie ckLoginDate = new Cookie("loginDate", System.currentTimeMillis() + "");

			response.addCookie(ckUsername);
			response.addCookie(ckLoginDate);

			// Session

			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("loginDate", System.currentTimeMillis() + "");

			response.sendRedirect("dashboard");

		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
			requestDispatcher.forward(request, response);
		}
	}  
	
	*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến login.jsp nếu truy cập bằng GET
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kết nối database và kiểm tra thông tin đăng nhập
        Connection connection = DbConnector.getConnection();
        String sql = "SELECT * FROM sem4_shop.users WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Đăng nhập thành công
                String email = resultSet.getString("email");
                Date lastLogin = resultSet.getTimestamp("lastLogin") != null ?
                        new Date(resultSet.getTimestamp("lastLogin").getTime()) : null;
                String imagePath = resultSet.getString("imagePath");

                // Cập nhật lastLogin trong database
                String updateSql = "UPDATE sem4_shop.users SET lastLogin = NOW() WHERE username = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setString(1, username);
                updateStmt.executeUpdate();

                // Lưu thông tin vào cookie
                Cookie ckUsername = new Cookie("username", username);
                Cookie ckLoginDate = new Cookie("loginDate", System.currentTimeMillis() + "");
                response.addCookie(ckUsername);
                response.addCookie(ckLoginDate);

                // Lưu thông tin vào session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("email", email);
                session.setAttribute("loginDate", lastLogin != null ? lastLogin.toString() : "First Login");
                session.setAttribute("imagePath", imagePath);

                // Chuyển hướng đến dashboard.jsp
                response.sendRedirect("dashboard.jsp");
            } else {
                // Đăng nhập thất bại
                request.setAttribute("errorMessage", "Invalid username or password");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred. Please try again.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}