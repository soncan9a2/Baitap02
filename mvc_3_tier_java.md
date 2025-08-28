# 📘 LẬP TRÌNH WEB (WEBPR330479)
**GV: ThS. Nguyễn Hữu Trung – ĐH SPKT TP.HCM**

---

## Nội dung
- Kiến trúc 3 tầng (Three Tier Architecture)  
- Các mô hình MVC  
- Ví dụ 1: Đăng nhập tài khoản  
- Ví dụ 2: Đăng ký tài khoản  
- Cấu trúc thư mục project  
- Bài tập áp dụng  

---

## 🏗️ Three Tier Architecture
1. **Presentation Layer:** hiển thị view, lấy data gửi controller.  
2. **Business Logic Layer:** xử lý logic (check username/pass, phân quyền).  
3. **Data Access Layer:** kết nối DB, query, trả về cho Business xử lý.  

Ví dụ kiến trúc:  
`Client (JSP) → Controller (UserController) → Service (UserService/UserServiceImpl) → DAO (UserDAO/UserDaoImpl) → Database`

---

## 🔄 Mô hình MVC
- **MVC1:** Browser → JSP → JavaBean → DB. (Nhược điểm: khó bảo trì, tốn công viết JSP tag, khó mở rộng).  
- **MVC2:** Browser → Servlet Controller → Model → DB → Controller → View → Browser. (Chuẩn hơn, tách biệt rõ).  

---

# 📂 Cấu trúc thư mục project (tham khảo)
```
WebApp_MVC/
│
├── src/
│   ├── controller/
│   │   ├── LoginController.java
│   │   ├── RegisterController.java
│   │   └── WaitingController.java
│   │
│   ├── dao/
│   │   ├── UserDao.java
│   │   └── UserDaoImpl.java
│   │
│   ├── model/
│   │   └── User.java
│   │
│   ├── service/
│   │   ├── UserService.java
│   │   └── UserServiceImpl.java
│   │
│   └── utils/
│       └── DBConnection.java
│
├── WebContent/
│   ├── views/
│   │   ├── login.jsp
│   │   ├── register.jsp
│   │   └── topbar.jsp
│   │
│   ├── META-INF/
│   └── WEB-INF/
│       ├── web.xml
│       └── lib/ (thư viện .jar JDBC, JSTL...)
│
└── pom.xml (nếu dùng Maven)
```

---

# 🧑‍💻 Ví dụ 1: Đăng nhập tài khoản

### Bước 1: View – `login.jsp`
```jsp
<form action="login" method="post">
<h2>Tạo tài khoản mới</h2>
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
</form>
```

### Bước 2: Model – `User.java`
```java
@SuppressWarnings("serial")
public class User implements Serializable {
    private int id;
    private String email;
    private String userName;
    private String fullName;
    private String passWord;
    private String avatar;
    private int roleid;
    private String phone;
    private Date createdDate;
    // constructor, getters/setters
}
```

### Bước 3: Interface Service
```java
public interface UserService {
    User login(String username, String password);
}
```

### Bước 4: Service Implement – `UserServiceImpl.java`
```java
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    public User get(String username) {
        return userDao.get(username);
    }
}
```

### Bước 5: Interface DAO
```java
public interface UserDao {
    User get(String username);
}
```

### Bước 6: DAO Implement – `UserDaoImpl.java`
```java
public class UserDaoImpl implements UserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getDate("createdDate"));
                return user;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
```

### Bước 7: Controller – `LoginController.java`
```java
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String alertMsg = "";

        if(username.isEmpty() || password.isEmpty()){
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.login(username, password);
        if(user != null){
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
```

### WaitingController.java
```java
@WebServlet(urlPatterns="/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            if(u.getRoleid()==1) {
                resp.sendRedirect(req.getContextPath()+"/admin/home");
            } else if(u.getRoleid()==2) {
                resp.sendRedirect(req.getContextPath()+"/manager/home");
            } else {
                resp.sendRedirect(req.getContextPath()+"/home");
            }
        } else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
```

### Bước 10: topbar.jsp
```jsp
<c:choose>
<c:when test="${sessionScope.account == null}">
  <a href="${pageContext.request.contextPath }/login">Đăng nhập</a> |
  <a href="${pageContext.request.contextPath }/register">Đăng ký</a>
</c:when>
<c:otherwise>
  <a href="${pageContext.request.contextPath }/member/myaccount">
    ${sessionScope.account.fullName}</a> |
  <a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a>
</c:otherwise>
</c:choose>
```

---

# 🧑‍💻 Ví dụ 2: Đăng ký tài khoản

### Bước 1: View – `register.jsp`
```jsp
<form action="register" method="post">
<h2>Tạo tài khoản mới</h2>
<c:if test="${alert !=null}">
 <h3 class="alert alert-danger">${alert}</h3>
</c:if>
<input type="text" placeholder="Tài khoản" name="username" class="form-control">
</form>
```

### Bước 3: Service Interface
```java
public interface UserService {
    void insert(User user);
    boolean register(String email, String password, String username,
                     String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}
```

### Bước 4: Service Implement
```java
@Override
public boolean register(String username, String password,
                        String email, String fullname, String phone) {
    if (userDao.checkExistUsername(username)) return false;
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);
    userDao.insert(new User(email, username, fullname, password,
                            null, 5, phone, date));
    return true;
}
```

### Bước 5: DAO Interface
```java
public interface UserDao {
    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}
```

### Bước 6: DAO Implement
```java
@Override
public void insert(User user) {
    String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid,phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
    try {
        conn = new DBConnection().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getFullName());
        ps.setString(4, user.getPassWord());
        ps.setString(5, user.getAvatar());
        ps.setInt(6, user.getRoleid());
        ps.setString(7, user.getPhone());
        ps.setDate(8, user.getCreatedDate());
        ps.executeUpdate();
    } catch (Exception e) {e.printStackTrace();}
}
```

### Bước 7: Controller – `RegisterController.java`
```java
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        UserService service = new UserServiceImpl();
        String alertMsg = "";

        if(service.checkExistEmail(email)){
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if(service.checkExistUsername(username)){
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = service.register(username, password, email, fullname, phone);
        if(isSuccess){
            resp.sendRedirect(req.getContextPath()+"/login");
        } else {
            alertMsg = "System error!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
```

---

# 📌 Bài tập áp dụng
- Làm lại 2 ví dụ (Login + Register).  

