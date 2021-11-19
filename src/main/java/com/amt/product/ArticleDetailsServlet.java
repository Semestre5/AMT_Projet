package com.amt.product;

import com.DAO.Access.ArticleOps;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/shop/*")
public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        url = request.getRequestURL().toString();
        int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

        request.setAttribute("articleDetails", ArticleOps.fetchOne(productId));
        RequestDispatcher rd = request.getRequestDispatcher("/articleDetails.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
