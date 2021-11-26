package com.amt.articleDetails;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.amt.shop.ShopServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/shop/*")
public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = request.getRequestURL().toString();
            int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

            request.setAttribute("articleDetails", ArticleOps.fetchOne(productId));
            RequestDispatcher rd = request.getRequestDispatcher("/articleDetails.jsp");
            rd.forward(request, response);
        } catch (Exception e){
            
            response.sendRedirect("http://localhost:8085/template_war_exploded");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
