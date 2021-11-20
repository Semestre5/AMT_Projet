package com.amt.catalog;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CatalogServlet", value = "/catalog")
public class CatalogServlet extends HttpServlet {

    public CatalogServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articles", ArticleOps.fetchAll());
        RequestDispatcher rd = request.getRequestDispatcher("catalog.jsp");
        rd.forward(request, response);
    }

}
