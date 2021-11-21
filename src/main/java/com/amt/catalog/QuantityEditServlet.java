package com.amt.catalog;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "QuantityEditServlet", value = "/editquantity")
public class QuantityEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer newQuantity = Integer.valueOf(request.getParameter("newQuantity"));
        Integer articleId = Integer.valueOf(request.getParameter("id"));
        Article editArticle = ArticleOps.fetchOne(articleId);
        if (newQuantity >= 0){
            editArticle.setQuantity(newQuantity);
        } else {
            editArticle.setQuantity(0);
        }
        ArticleOps.updateArticle(editArticle);
        response.sendRedirect(request.getContextPath() + "/catalog");
    }
}
