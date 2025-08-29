# Bài tập 02 - Login MVC 3 Tier

**Sinh viên:** Huỳnh Thanh Nhân  
**MSSV:** 23110280

## Mô tả
Web application Java sử dụng kiến trúc MVC 3 tầng với các chức năng:
- Đăng nhập, đăng ký, quên mật khẩu
- Quản lý Category với upload ảnh
- Xử lý session và cookie

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

-- Thêm dữ liệu mẫu User
INSERT INTO [User] (email, username, fullname, password, roleid, phone, createdDate) 
VALUES 
('admin@test.com', 'admin', 'Administrator', '123456', 1, '0123456789', GETDATE()),
('manager@test.com', 'manager', 'Manager User', '123456', 2, '0987654321', GETDATE()),
('user@test.com', 'user', 'Normal User', '123456', 3, '0111222333', GETDATE());

-- Tạo bảng Category
CREATE TABLE Category(
    cate_id INT IDENTITY(1,1) NOT NULL,
    cate_name NVARCHAR(100) NOT NULL,
    icons NVARCHAR(500) NULL,
    user_id INT NOT NULL,
    created_date DATE DEFAULT GETDATE(),
    CONSTRAINT PK_Category PRIMARY KEY CLUSTERED (cate_id ASC),
    CONSTRAINT FK_Category_User FOREIGN KEY (user_id) REFERENCES [User](id)
);

-- Thêm dữ liệu mẫu Category , ở đây ảnh sẽ rỗngrỗng
INSERT INTO Category (cate_name, icons, user_id) VALUES 
(N'Công nghệ', 'tech.jpg', 1),
(N'Du lịch', 'travel.jpg', 1),
(N'Ẩm thực', 'food.jpg', 1);
```

## Chạy ứng dụng
1. Tạo database SQL Server như trên
2. Tạo thư mục `C:\upload` để lưu ảnh
3. Cấu hình kết nối DB trong `DBConnection.java`
4. Build: `mvn clean package`
5. Deploy file WAR lên Tomcat
6. Truy cập: `http://localhost:8080/Baitap02/login`

## Tài khoản test
- admin/123456 (Admin)
- manager/123456 (Manager)  
- user/123456 (User)