# Bài tập 02 - Login MVC 3 Tier

**Sinh viên:** Huỳnh Thanh Nhân  
**MSSV:** 23110280

## Mô tả
Web application Java sử dụng kiến trúc MVC 3 tầng để xây dựng tính năng đăng nhập người dùng với xử lý session và cookie.

## Tạo Database
```sql
CREATE DATABASE LTWeb;
GO

USE LTWeb;
GO

CREATE TABLE [User] (
    id int IDENTITY(1,1) PRIMARY KEY,
    email nvarchar(255),
    username nvarchar(50) UNIQUE,
    fullname nvarchar(100),
    password nvarchar(255),
    avatar nvarchar(255),
    roleid int,
    phone nvarchar(20),
    createdDate date
);

-- Thêm dữ liệu mẫu
INSERT INTO [User] (email, username, fullname, password, roleid, phone, createdDate) 
VALUES 
('admin@test.com', 'admin', 'Administrator', '123456', 1, '0123456789', GETDATE()),
('manager@test.com', 'manager', 'Manager User', '123456', 2, '0987654321', GETDATE()),
('user@test.com', 'user', 'Normal User', '123456', 3, '0111222333', GETDATE());
```

## Chạy ứng dụng
1. Tạo database SQL Server như trên
2. Cấu hình kết nối DB trong `DBConnection.java`
3. Deploy lên Tomcat
4. Truy cập: `http://localhost:8080/Baitap02/login`

## Tài khoản test
- admin/123456 (Admin)
- manager/123456 (Manager)  
- user/123456 (User)