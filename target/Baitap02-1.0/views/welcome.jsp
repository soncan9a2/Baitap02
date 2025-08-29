<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chào mừng</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<style>
    .welcome-container {
        margin-top: 100px;
        text-align: center;
    }
    .welcome-message {
        font-size: 28px;
        color: #2c3e50;
        margin-bottom: 30px;
    }
    .user-info {
        background-color: #f8f9fa;
        padding: 20px;
        border-radius: 10px;
        margin: 20px auto;
        max-width: 400px;
    }
</style>
</head>
<body>
<div class="container">
    <div class="welcome-container">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h2><i class="fa fa-home"></i> Chào mừng đến với hệ thống</h2>
            </div>
            <div class="panel-body">
                <c:if test="${user != null}">
                    <div class="welcome-message">
                        <i class="fa fa-user-circle-o"></i>
                        Chào mừng <strong>${user.userName}</strong>!
                    </div>
                    <div class="user-info">
                        <p><strong>Tên đăng nhập:</strong> ${user.userName}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Họ tên:</strong> ${user.fullName}</p>
                        <c:choose>
                            <c:when test="${user.roleid == 1}">
                                <p><strong>Vai trò:</strong> <span class="label label-danger">Quản trị viên</span></p>
                            </c:when>
                            <c:when test="${user.roleid == 2}">
                                <p><strong>Vai trò:</strong> <span class="label label-warning">Quản lý</span></p>
                            </c:when>
                            <c:otherwise>
                                <p><strong>Vai trò:</strong> <span class="label label-info">Người dùng</span></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
                <div style="margin-top: 20px;">
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">
                        <i class="fa fa-sign-out"></i> Đăng xuất
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
