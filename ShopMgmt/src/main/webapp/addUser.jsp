<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add New User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #1f4037, #99f2c8);
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-size: cover;
            background-repeat: no-repeat;
        }
        .form-container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            padding: 40px;
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .header-banner {
            margin-bottom: 20px;
            color: #ff9900;
            font-size: 28px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .subtext {
            margin-bottom: 20px;
            font-size: 16px;
            color: #666;
        }
        .form-container h2 {
            font-weight: bold;
            margin-bottom: 30px;
            color: #333;
        }
        .form-container .form-control {
            border-radius: 50px;
            padding: 15px;
        }
        .btn-custom {
            background-color: #ff9900;
            border: none;
            border-radius: 50px;
            padding: 10px 20px;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #e68a00;
        }
        .btn-cancel {
            background-color: #6c757d;
            border: none;
            border-radius: 50px;
            padding: 10px 20px;
            font-weight: bold;
            font-size: 16px;
            color: white;
            transition: background-color 0.3s ease;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        .btn-cancel:hover {
            background-color: #5a6268;
            color: white;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="header-banner">VIP Pro Sales</div>
        <div class="subtext">Create a new user account</div>
        <h2>Add New User</h2>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        <form action="user?action=create" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <div class="mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
            </div>
            <div class="mb-3">
                <label for="imagePath" class="form-label">User Image</label>
                <input type="file" class="form-control" id="imagePath" name="imagePath" accept="image/*">
            </div>
            <button type="submit" class="btn btn-custom w-100">Create User</button>
        </form>
        <a href="login.jsp" class="btn btn-cancel w-100">Cancel</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>