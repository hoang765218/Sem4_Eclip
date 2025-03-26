<%@ page language="java" contentType="text/html; charset=UTF-8" 
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Home Page</title>
    <!-- Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
    />
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .navbar-brand {
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .hero {
            background: linear-gradient(135deg, #1f4037, #99f2c8);
            padding: 60px 20px;
            color: #fff;
            text-align: center;
        }
        .hero h1 {
            font-size: 48px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .hero p {
            font-size: 20px;
        }
        .content-section {
            padding: 40px 20px;
        }
        .footer {
            background-color: #343a40;
            color: #fff;
            padding: 15px 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Thanh điều hướng (Navbar) -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">My Shop</a>
            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="home.jsp">Home</a>
                    </li>
                    <!-- Thêm các menu khác nếu cần -->
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Sales</a>
                    </li>
                    <!-- Nút logout ví dụ, tùy thuộc cấu trúc app -->
                    <li class="nav-item">
                        <a class="nav-link" href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Phần nội dung chính -->
    <div class="hero">
        <h1>Welcome to the Home Page</h1>
        <p>Your login was successful. Explore your dashboard below.</p>
    </div>

    <div class="content-section container">
        <h2>Overview</h2>
        <p>
            This is where you can put a brief overview of the application, recent updates,
            or shortcuts to various functionalities such as product management, sales
            tracking, and more.
        </p>

        <hr />

        <h3>Quick Actions</h3>
        <div class="row">
            <div class="col-md-4 mb-3">
                <button class="btn btn-primary w-100">Manage Products</button>
            </div>
            <div class="col-md-4 mb-3">
                <button class="btn btn-warning w-100">View Sales</button>
            </div>
            <div class="col-md-4 mb-3">
                <button class="btn btn-success w-100">Add New Items</button>
            </div>
        </div>
    </div>

    <!-- Phần footer -->
    <footer class="footer">
        <p>&copy; 2025 My Shop. All rights reserved.</p>
    </footer>

    <!-- Bootstrap JS -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    ></script>
</body>
</html>
