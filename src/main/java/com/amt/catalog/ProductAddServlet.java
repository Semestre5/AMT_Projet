package com.amt.catalog;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ProductAddServlet", value = "/product.add")
public class ProductAddServlet extends HttpServlet {
    private static final String BASE_IMG_URL = "https://media.istockphoto.com/vectors/yellow-warning-sign-work-in-progress-background-vector-id1253437873?k=20&m=1253437873&s=612x612&w=0&h=S0hzCYCgPCufkdZaHlCDTCu_LP066lD99njJm8RuPgg=";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("productadd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productDesc = request.getParameter("productDesc");
        String productPriceInput = request.getParameter("productPrice");
        BigDecimal productPrice = BigDecimal.valueOf(0);
        String productImg = request.getParameter("productImg");
        String productName = request.getParameter("productName");
        Integer productQuantity = Integer.valueOf(request.getParameter("productNumber"));
        if(productDesc == ""){
            productDesc = "The seller hasn't implemented a description yet";
        }
        if(productPriceInput != ""){
            productPrice =  BigDecimal.valueOf(Long.parseLong(productPriceInput));
        }
        if(productImg == ""){
            productImg = BASE_IMG_URL;
        }
        Article newArticle = new Article(productPrice,productDesc,productName,productQuantity, productImg);
        List<Article> currentArticles = ArticleOps.fetchAll();
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
            response.sendRedirect(request.getContextPath() + "/catalog");
        }else{
            request.setAttribute("errorDuplicate", true);
            request.setAttribute("duplicate", duplicate);
            RequestDispatcher rd = request.getRequestDispatcher("productadd.jsp");
            rd.forward(request, response);
        }
    }
}
