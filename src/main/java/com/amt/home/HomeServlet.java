package com.amt.home;

import com.DAO.Access.ArticleOps;
import com.amt.authentication.CheckCredentials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

    public static final String ARTICLES_ATTR = "articles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CheckCredentials.isAdmin(request)) {
            request.setAttribute(ARTICLES_ATTR, ArticleOps.fetchLastThree());
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "shopManagement");
        }
    }
}
