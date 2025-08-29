<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="forgot-password" method="post">
                        <h2>Quên mật khẩu</h2>
                        <c:if test="${alert != null}">
                            <h3 class="alert alert-danger">${alert}</h3>
                        </c:if>
                        <c:if test="${success != null}">
                            <h3 class="alert alert-success">${success}</h3>
                        </c:if>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="text" placeholder="Tên đăng nhập" name="username" class="form-control" required>
                                </div>
                            </label>
                        </section>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input type="email" placeholder="Email" name="email" class="form-control" required>
                                </div>
                            </label>
                        </section>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                                    <input type="text" placeholder="Số điện thoại" name="phone" class="form-control" required>
                                </div>
                            </label>
                        </section>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="password" placeholder="Mật khẩu mới" name="newPassword" class="form-control" required>
                                </div>
                            </label>
                        </section>
                        <section>
                            <button type="submit" class="btn btn-primary btn-block">Đặt lại mật khẩu</button>
                        </section>
                        <section>
                            <p>Quay lại <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
                        </section>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
