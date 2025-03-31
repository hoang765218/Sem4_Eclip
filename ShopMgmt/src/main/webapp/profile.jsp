<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Profile</title>
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
        .profile-container {
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
        .profile-container h2 {
            font-weight: bold;
            margin-bottom: 30px;
            color: #333;
        }
        .profile-image {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            margin-bottom: 20px;
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
        .btn-download {
            background-color: #28a745;
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
        .btn-download:hover {
            background-color: #218838;
            color: white;
        }
        .btn-upload {
            background-color: #007bff;
            border: none;
            border-radius: 50px;
            padding: 10px 20px;
            font-weight: bold;
            font-size: 16px;
            color: white;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        }
        .btn-upload:hover {
            background-color: #0056b3;
            color: white;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <div class="header-banner">VIP Pro Sales</div>
        <div class="subtext">Your Profile</div>
        <h2>User Profile</h2>
        <% if (session.getAttribute("imagePath") != null) { %>
            <img src="${pageContext.request.contextPath}${imagePath}" alt="User Image" class="profile-image">
        <% } else { %>
            <img src="${pageContext.request.contextPath}/images/default-user.jpg" alt="Default User Image" class="profile-image">
        <% } %>
        <p><strong>Username:</strong> ${username}</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Last Login:</strong> ${loginDate}</p>

        <!-- Form upload hình ảnh -->
        <form action="user?action=uploadImage" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="imagePath" class="form-label">Upload New Image</label>
                <input type="file" class="form-control" id="imagePath" name="imagePath" accept="image/*">
            </div>
            <button type="submit" class="btn btn-upload w-100">Upload Image</button>
        </form>

        <!-- Nút Download hình ảnh -->
        <% if (session.getAttribute("imagePath") != null) { %>
            <a href="user?action=downloadImage" class="btn btn-download w-100">Download Image</a>
        <% } %>

        <a href="dashboard.jsp" class="btn btn-custom w-100 mt-3">Back to Dashboard</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>