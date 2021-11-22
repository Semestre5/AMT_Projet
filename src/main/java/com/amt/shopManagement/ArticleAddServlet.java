package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ArticleAddServlet", value = "/articleAdd")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
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

        Part filePart = request.getPart("image");
        if (filePart != null) {
            String fileName = filePart.getSubmittedFileName();
            String uploadRelativePath = "\\resources\\images\\";
            for (Part part : request.getParts()) {
                String uploadPath = getServletContext().getRealPath("") + "\\..\\..\\src\\main\\webapp" + uploadRelativePath + fileName;
                part.write(uploadPath);
            }
            imageLink = "./resources/images/" + fileName;
        } else {
            imageLink = BASE_IMG_URL;
        }
        if(description == ""){
            description = "The seller hasn't implemented a description yet";
        }
        if(priceInput != ""){
            price =  BigDecimal.valueOf(Long.parseLong(priceInput));
        }
        Article newArticle = new Article(price,description,name,quantity, imageLink);
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
}
