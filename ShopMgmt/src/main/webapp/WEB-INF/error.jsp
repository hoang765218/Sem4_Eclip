<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error Page</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f2dede;
      font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
      height: 100vh;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .error-container {
      text-align: center;
      padding: 40px;
      border: 1px solid #ebccd1;
      border-radius: 10px;
      background-color: #f9e2e2;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
    .error-code {
      font-size: 72px;
      font-weight: bold;
      color: #a94442;
    }
    .error-message {
      font-size: 24px;
      color: #a94442;
    }
    .error-details {
      margin-top: 20px;
      color: #a94442;
    }
  </style>
</head>
<body>
  <div class="container error-container">
    <div class="error-code">Error</div>
    <div class="error-message">
      <p>An unexpected error has occurred.</p>
    </div>
    <div class="error-details">
      <%-- Hiển thị thông báo chi tiết lỗi nếu có --%>
      <% 
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (exception != null) {
          out.println("<p>" + exception.getMessage() + "</p>");
        } else {
          out.println("<p>Please contact support or try again later.</p>");
        }
      %>
    </div>
    <a href="home.jsp" class="btn btn-primary mt-3">Back to Home</a>
  </div>
  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
