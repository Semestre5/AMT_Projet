package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShopManagementServlet", value = "/shopManagement")
public class ShopManagementServlet extends HttpServlet {
    public static final String CATEGORIES = "categories";
    public static final String ARTICLES = "articles";
    public ShopManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String roleUser = (String) session.getAttribute("roleUser");
        if (session != null && roleUser.equals("admin")) {
            request.setAttribute(ARTICLES, ArticleOps.fetchAll());
            request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
            RequestDispatcher rd = request.getRequestDispatcher("shopManagement.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("articleId")!= null && request.getParameter("categoryId") != null){

        }
    }
}
