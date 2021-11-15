package com.amt.product;

import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/shop/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        url = (String) request.getRequestURL().toString();
        url = url == null ? request.getRequestURI() : url;
        int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

        request.setAttribute("articleDetails", productId == 1 ? Article.TEST_ARTICLE1: Article.TEST_ARTICLE2);
        RequestDispatcher rd = request.getRequestDispatcher("/articleDetails.jsp");
        rd.forward(request, response);
        System.out.print("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
