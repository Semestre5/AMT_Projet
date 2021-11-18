package com.amt.catalog;

import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ProductAddServlet", value = "/product.add")
public class ProductAddServlet extends HttpServlet {
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
            productImg  = "placeholderfordefaultPFP";
        }
        Article newArticle = new Article(2,productPrice,productDesc,productName,productQuantity, productImg);

        //TODO add the article to the DB not sure how to do that
    }
}
