package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String newPassword = req.getParameter("newPassword");
        String alertMsg = "";

        if(username == null || email == null || phone == null || newPassword == null || 
           username.isEmpty() || email.isEmpty() || phone.isEmpty() || newPassword.isEmpty()){
            alertMsg = "Vui lòng điền đầy đủ thông tin";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        boolean isReset = service.resetPassword(username, email, phone, newPassword);
        
        if(isReset){
            req.setAttribute("success", "Đặt lại mật khẩu thành công! Bạn có thể đăng nhập với mật khẩu mới.");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        } else {
            alertMsg = "Thông tin không chính xác. Vui lòng kiểm tra lại username, email và số điện thoại.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
