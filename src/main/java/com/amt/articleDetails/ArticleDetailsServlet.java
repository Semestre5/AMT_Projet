package com.amt.articleDetails;

import com.DAO.Access.ArticleOps;
import com.amt.authentication.CheckCredentials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/shop/*")
public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CheckCredentials.isAdmin(request)) {
            try {
                String url = request.getRequestURL().toString();
                int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

                request.setAttribute("articleDetails", ArticleOps.fetchOne(productId));
                RequestDispatcher rd = request.getRequestDispatcher("/articleDetails.jsp");
                rd.forward(request, response);
            } catch (Exception e){
                response.sendRedirect("home");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "home");
        }
    }
}
