package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import com.amt.authentication.CheckCredentials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ArticleAddServlet", value = "/articleAdd")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 10  // 10 MB
)
public class ArticleAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckCredentials.isAdmin(request)) {
            RequestDispatcher rd = request.getRequestDispatcher("articleAdd.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckCredentials.isAdmin(request)) {
            // we get the parameters
            String name = request.getParameter("name");
            Integer quantity = Integer.valueOf(request.getParameter("quantity"));
            String description = request.getParameter("description");
            String priceInput = request.getParameter("price");
            BigDecimal price = BigDecimal.valueOf(0);

            if(description == null || description.isEmpty()){
                description = "The seller hasn't implemented a description yet";
            }
            if(priceInput != null && !priceInput.isEmpty()){
                price =  BigDecimal.valueOf(Double.parseDouble(priceInput));
            }
            Integer id = ArticleOps.isStored(name);
            if(id == null){
                // we try to upload only when we are sure the other parameter are ok
                String imageLink = uploadImage(request);
                Article newArticle = new Article(price, description, name, quantity, imageLink);
                ArticleOps.registerArticle(newArticle);
                response.sendRedirect(request.getContextPath() + "/shopManagement");
            }else{
                request.setAttribute("duplicatedName", name);
                request.setAttribute("duplicatedID", id);
                RequestDispatcher rd = request.getRequestDispatcher("articleAdd.jsp");
                rd.forward(request, response);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "home");
        }
    }

    private String uploadImage(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            File uploadDir = new File(getServletContext().getRealPath("/images"));
            if (!uploadDir.exists())
                uploadDir.mkdir();
            String fullPath = getServletContext().getRealPath("/images") + File.separator + fileName;
            filePart.write(fullPath);
            return "./images" + File.separator + fileName;
        }
        else{
            return "./images/DEFAULT_IMAGE.jpg";
        }
    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
