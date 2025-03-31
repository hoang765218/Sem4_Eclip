<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shop Management - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <style>
        body {
            background-color: #f0f4f8;
            min-height: 100vh;
        }
        .navbar {
            background-color: #2c6e49;
        }
        .navbar-brand {
            color: #f4a261 !important;
            font-weight: 700;
            font-size: 1.5rem;
        }
        .navbar-brand .logo {
            font-size: 2rem;
            margin-right: 10px;
        }
        .btn-logout {
            background-color: #e76f51;
            border: none;
            color: white;
        }
        .btn-logout:hover {
            background-color: #d65a3d;
            color: white;
        }
        .dashboard-content {
            margin-top: 50px;
            padding: 20px;
            max-width: 800px;
            margin-left: auto;
            margin-right: auto;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #2c6e49;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <i class="fas fa-store logo"></i> Shop Management
            </a>
            <div class="ms-auto">
                <span class="text-white me-3">
                   Welcome, <a href="profile.jsp" class="nav-link-profile">${username}</a> (Last login: ${loginDate})
                </span>
                <form action="logout" method="post" class="d-inline">
                    <button type="submit" class="btn btn-logout">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="dashboard-content">
        <h2>Welcome to Shop Management</h2>
        <p>This is your dashboard, ${username}. You can manage your shop here.</p>
        <p>Last login: ${loginDate}</p>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Products</h5>
                        <p class="card-text">Manage your product inventory.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Orders</h5>
                        <p class="card-text">View and process customer orders.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>