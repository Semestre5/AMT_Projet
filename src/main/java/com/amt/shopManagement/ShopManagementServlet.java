package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShopManagementServlet", value = "/shopManagement")
public class ShopManagementServlet extends HttpServlet {

    public ShopManagementServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String roleUser = (String) session.getAttribute("roleUser");
        if (session != null && roleUser.equals("admin")) {
            request.setAttribute("articles", ArticleOps.fetchAll());
            RequestDispatcher rd = request.getRequestDispatcher("shopManagement.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect(".");
        }
    }
}
