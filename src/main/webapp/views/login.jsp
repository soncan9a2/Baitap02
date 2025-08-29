<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="login" method="post">
                        <h2>Đăng nhập</h2>
                        <c:if test="${alert !=null}">
                            <h3 class="alert alert-danger">${alert}</h3>
                        </c:if>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="text" placeholder="Tài khoản" name="username" class="form-control">
                                </div>
                            </label>
                        </section>
                        <section>
                            <label class="input login-input">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control">
                                </div>
                            </label>
                        </section>
                        <section>
                            <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
                        </section>
                        <section>
                            <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
                        </section>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
