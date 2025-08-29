<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Category</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f8f9fa;
    }
    .container {
        max-width: 600px;
        margin: 0 auto;
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .header {
        text-align: center;
        margin-bottom: 30px;
    }
    .form-group {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        color: #333;
    }
    input[type="text"], input[type="file"] {
        width: 100%;
        padding: 12px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 14px;
    }
    .btn {
        padding: 12px 30px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        text-decoration: none;
        display: inline-block;
        margin: 5px;
    }
    .btn-primary {
        background-color: #28a745;
        color: white;
    }
    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }
    .btn:hover {
        opacity: 0.9;
    }
    .buttons {
        text-align: center;
        margin-top: 30px;
    }
    .message {
        color: red;
        text-align: center;
        margin-bottom: 20px;
    }
    .current-image {
        text-align: center;
        margin: 15px 0;
    }
    .current-image img {
        max-width: 200px;
        max-height: 150px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Sửa Category</h1>
        </div>
        
        <c:if test="${message != null}">
            <div class="message">
                ${message}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${category.cateid}">
            
            <div class="form-group">
                <label for="name">Tên danh mục: <span style="color: red;">*</span></label>
                <input type="text" id="name" name="name" value="${category.catename}" required placeholder="Nhập tên danh mục...">
            </div>
            
            <div class="form-group">
                <label for="icon">Ảnh đại diện hiện tại:</label>
                <c:if test="${category.icon != null && category.icon != ''}">
                    <div class="current-image">
                        <c:url value="/image?fname=${category.icon}" var="imgUrl"></c:url>
                        <img src="${imgUrl}" alt="${category.catename}" />
                        <p><small>Ảnh hiện tại: ${category.icon}</small></p>
                    </div>
                </c:if>
                <c:if test="${category.icon == null || category.icon == ''}">
                    <div class="current-image">
                        <p><small>Chưa có ảnh</small></p>
                    </div>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="icon">Thay đổi ảnh đại diện:</label>
                <input type="file" id="icon" name="icon" accept="image/*">
                <small style="color: #666;">Chọn file ảnh mới (jpg, png, gif...) - để trống nếu không muốn thay đổi</small>
            </div>
            
            <div class="buttons">
                <button type="submit" class="btn btn-primary">Cập nhật Category</button>
                <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>
</body>
</html>
