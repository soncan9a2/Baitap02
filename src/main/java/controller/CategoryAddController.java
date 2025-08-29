package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Category;
import model.User;
import service.CategoryService;
import service.CategoryServiceImpl;
import utils.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10,      
                 maxRequestSize = 1024 * 1024 * 50)   
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Kiểm tra session user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Kt session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Lay ten
            String catename = req.getParameter("name");
            String fname = "";

            // upload
            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                fname = System.currentTimeMillis() + "_" + fileName;

                // Neu chua ton tai thu muc
                Path uploadDir = Paths.get(Constant.DIR);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fname);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // tao doi tuong
            Category category = new Category();
            category.setCatename(catename);
            category.setIcon(fname);
            category.setUserId(user.getId());

            cateService.insert(category);

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi khi upload file: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
