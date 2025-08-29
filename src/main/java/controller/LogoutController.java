package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    	// xoa sesion
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // xoa cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(req.getContextPath());
                    resp.addCookie(cookie);
                }
            }
        }
        
        // Redirect về trang login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
