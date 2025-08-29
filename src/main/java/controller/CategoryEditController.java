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

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CategoryEditController extends HttpServlet {
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
        
        int cateid = Integer.parseInt(req.getParameter("id"));
        Category category = cateService.get(cateid);
        
        req.setAttribute("category", category);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Kiểm tra session user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Thiết lập encoding
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Lấy thông tin từ form
            String catename = req.getParameter("name");
            int cateid = Integer.parseInt(req.getParameter("id"));
            String fname = "";

            // Lấy category cũ
            Category category = cateService.get(cateid);
            String fileold = category.getIcon();

            // Xử lý file upload
            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                fname = System.currentTimeMillis() + "_" + fileName;

                // Tạo thư mục nếu chưa tồn tại
                Path uploadDir = Paths.get(Constant.DIR);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Lưu file
                Path filePath = uploadDir.resolve(fname);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // Cập nhật thông tin
            category.setCatename(catename);
            category.setUserId(user.getId());
            
            // Nếu có file mới thì cập nhật, không thì giữ file cũ
            if (!fname.isEmpty()) {
                category.setIcon(fname);
            } else {
                category.setIcon(fileold);
            }

            // Update vào database
            cateService.edit(category);

            // Redirect về list
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi khi upload file: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
