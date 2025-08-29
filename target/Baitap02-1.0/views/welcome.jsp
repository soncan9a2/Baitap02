<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chào mừng</title>
<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        margin-top: 200px;
    }
    .welcome-message {
        font-size: 24px;
        margin-bottom: 30px;
    }
    .logout-btn {
        background-color: #dc3545;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        border: none;
        cursor: pointer;
    }
    .logout-btn:hover {
        background-color: #c82333;
        text-decoration: none;
        color: white;
    }
    .category-btn {
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        margin-right: 10px;
    }
    .category-btn:hover {
        background-color: #0056b3;
        text-decoration: none;
        color: white;
    }
</style>
</head>
<body>
    <c:if test="${user != null}">
        <div class="welcome-message">
            Chào mừng, ${user.userName}!
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/admin/category/list" class="category-btn">Quản lý Category</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
        </div>
    </c:if>
</body>
</html>
