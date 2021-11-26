package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;

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
    private static final String BASE_IMG_URL = "https://media.istockphoto.com/vectors/yellow-warning-sign-work-in-progress-background-vector-id1253437873?k=20&m=1253437873&s=612x612&w=0&h=S0hzCYCgPCufkdZaHlCDTCu_LP066lD99njJm8RuPgg=";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("articleAdd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String description = request.getParameter("description");
        String priceInput = request.getParameter("price");
        BigDecimal price = BigDecimal.valueOf(0);
        String imageLink = request.getParameter("image");
        String name = request.getParameter("name");
        Integer quantity = Integer.valueOf(request.getParameter("quantity"));
        uploadImage(request.getPart("image"));
        if(Objects.equals(description, "")){
            description = "The seller hasn't implemented a description yet";
        }
        /*if(!Objects.equals(priceInput, "")){
            price =  BigDecimal.valueOf(Long.parseLong(priceInput));
        }*/
        Article newArticle = new Article(BigDecimal.valueOf(1),description,name,quantity, imageLink);
        List<Article> currentArticles = (List<Article>) ArticleOps.fetchAll();
        boolean already_exists = false;
        Article duplicate = null;
        for(Article a: currentArticles){
            if (a.getName().equals(newArticle.getName())){
                already_exists = true;
                duplicate = a;
            }
        }
        if(!already_exists){
            ArticleOps.registerArticle(newArticle);
            response.sendRedirect(request.getContextPath() + "/shopManagement");
        }else{
            request.setAttribute("errorDuplicate", true);
            request.setAttribute("duplicate", duplicate);
            RequestDispatcher rd = request.getRequestDispatcher("articleAdd.jsp");
            rd.forward(request, response);
        }
    }

    private void uploadImage(Part filePart) throws IOException {
        if (filePart != null) {
            String fileName = getFileName(filePart);
            File uploadDir = new File( getServletContext().getRealPath("/images") );
            if ( ! uploadDir.exists() )
                uploadDir.mkdir();
            String fullPath = getServletContext().getRealPath("/images") + File.separator + fileName;
            filePart.write(fullPath);
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
