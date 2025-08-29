package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Constant;

@WebServlet(urlPatterns = "/image")
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        File file = new File(Constant.DIR + "/" + fileName);
        
        if (file.exists()) {
            // Xác định content type dựa trên phần mở rộng file
            String contentType = getServletContext().getMimeType(fileName);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            resp.setContentType(contentType);
            resp.setContentLength((int) file.length());
            
            // Copy file content to response
            try (FileInputStream fis = new FileInputStream(file);
                 OutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
