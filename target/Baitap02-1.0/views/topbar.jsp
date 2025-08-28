<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebApp</a>
    </div>
    <div class="navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${sessionScope.account == null}">
            <li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
            <li><a href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/member/myaccount">
              ${sessionScope.account.fullName}</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
