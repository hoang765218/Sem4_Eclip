
<!-- Phần còn lại của dashboard.jsp giữ nguyên -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Quản lý hệ thống</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome cho icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        .sidebar {
            min-height: 100vh;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        }
        .card-hover:hover {
            transform: translateY(-5px);
            transition: all 0.3s ease;
        }
        .navbar {
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <!-- Navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Dashboard</a>
            <div class="d-flex">
                <span class="navbar-text me-3">Xin chào, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Khách" %></span>
               
                <!-- Thay dòng này trong navbar -->
<a href="${pageContext.request.contextPath}/logout.jsp" class="btn btn-outline-danger">Đăng xuất</a>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
                <div class="position-sticky pt-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <i class="fas fa-home me-2"></i>Trang chủ
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-users me-2"></i>Quản lý người dùng
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-chart-bar me-2"></i>Thống kê
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-cog me-2"></i>Cài đặt
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Main content -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Tổng quan hệ thống</h1>
                </div>

                <!-- Dashboard cards -->
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="card card-hover">
                            <div class="card-body">
                                <h5 class="card-title"><i class="fas fa-users me-2"></i>Tổng người dùng</h5>
                                <h2 class="card-text">1,234</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card card-hover">
                            <div class="card-body">
                                <h5 class="card-title"><i class="fas fa-shopping-cart me-2"></i>Đơn hàng hôm nay</h5>
                                <h2 class="card-text">56</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card card-hover">
                            <div class="card-body">
                                <h5 class="card-title"><i class="fas fa-dollar-sign me-2"></i>Doanh thu</h5>
                                <h2 class="card-text">12,345,678 VNĐ</h2>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Additional content -->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin hệ thống</h5>
                        <p>Thời gian hiện tại: <%= new java.util.Date() %></p>
                        <p>Phiên bản hệ thống: 1.0.0</p>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>