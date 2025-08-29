<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Category</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    .container {
        max-width: 1200px;
        margin: 0 auto;
    }
    .header {
        text-align: center;
        margin-bottom: 30px;
    }
    .add-btn {
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        margin-bottom: 20px;
        display: inline-block;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        text-align: center;
    }
    td {
        text-align: center;
    }
    .action-links a {
        margin: 0 5px;
        padding: 5px 10px;
        text-decoration: none;
        border-radius: 3px;
    }
    .edit-link {
        background-color: #28a745;
        color: white;
    }
    .delete-link {
        background-color: #dc3545;
        color: white;
    }
    .back-link {
        background-color: #6c757d;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        margin-right: 10px;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Quản lý Category</h1>
            <a href="${pageContext.request.contextPath}/welcome" class="back-link">← Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/admin/category/add" class="add-btn">+ Thêm Category mới</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Ảnh đại diện</th>
                    <th>Tên Category</th>
                    <th>Ngày tạo</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cateList}" var="cate" varStatus="STT">
                    <tr>
                        <td>${STT.index+1}</td>
                        <td>
                            <c:if test="${cate.icon != null && cate.icon != ''}">
                                <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                                <img height="100" width="150" src="${imgUrl}" alt="${cate.catename}" />
                            </c:if>
                            <c:if test="${cate.icon == null || cate.icon == ''}">
                                <span>Không có ảnh</span>
                            </c:if>
                        </td>
                        <td>${cate.catename}</td>
                        <td>${cate.createdDate}</td>
                        <td class="action-links">
                            <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>" class="edit-link">Sửa</a>
                            <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>" class="delete-link" 
                               onclick="return confirm('Bạn có chắc muốn xóa category này?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <c:if test="${empty cateList}">
            <div style="text-align: center; margin-top: 50px;">
                <h3>Chưa có category nào. <a href="${pageContext.request.contextPath}/admin/category/add">Thêm mới ngay!</a></h3>
            </div>
        </c:if>
    </div>
</body>
</html>
