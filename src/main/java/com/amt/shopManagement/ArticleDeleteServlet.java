package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import com.amt.authentication.CheckCredentials;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import com.amt.authentication.CheckCredentials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteArticleServlet", value = "/deleteArticle")
public class ArticleDeleteServlet extends HttpServlet  {
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ( CheckCredentials.isAdmin(request)) {

            Integer articleId = Integer.valueOf(request.getParameter("id"));
            ArticleOps.deleteArticle(articleId);
            response.sendRedirect(request.getContextPath() + "/shopManagement");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "home");
        }
    }
}
