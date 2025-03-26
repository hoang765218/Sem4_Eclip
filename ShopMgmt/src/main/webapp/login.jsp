<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>VIP Pro Sales - Login</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom Styles for VIP Pro Sales Login -->
  <style>
    /* Nền gradient tạo cảm giác sang trọng */
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
    /* Container chính của form login */
    .login-container {
      background: rgba(255, 255, 255, 0.95);
      border-radius: 15px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
      padding: 40px;
      max-width: 400px;
      width: 100%;
      text-align: center;
    }
    /* Tiêu đề banner */
    .header-banner {
      margin-bottom: 20px;
      color: #ff9900;
      font-size: 28px;
      font-weight: bold;
      text-transform: uppercase;
      letter-spacing: 1px;
    }
    /* Phần phụ đề */
    .subtext {
      margin-bottom: 20px;
      font-size: 16px;
      color: #666;
    }
    /* Style cho tiêu đề form */
    .login-container h2 {
      font-weight: bold;
      margin-bottom: 30px;
      color: #333;
    }
    /* Style cho input fields */
    .login-container .form-control {
      border-radius: 50px;
      padding: 15px;
    }
    /* Button phong cách VIP */
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
    /* Đặt vị trí cho checkbox */
    .form-check-label {
      margin-left: 5px;
    }
  </style>
</head>
<body>
  <div class="login-container">
    <div class="header-banner">VIP Pro Sales</div>
    <div class="subtext">Welcome to the exclusive sales portal</div>
    <h2>Login</h2>
    <form action="login" method="post">
      <div class="mb-3">
        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
      </div>
      <div class="mb-3">
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
      </div>
      <div class="mb-3 form-check text-start">
        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
        <label class="form-check-label" for="rememberMe">Remember me</label>
      </div>
      <button type="submit" class="btn btn-custom w-100">Login</button>
    </form>
  </div>
  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>