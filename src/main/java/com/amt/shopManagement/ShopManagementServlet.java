package com.amt.shopManagement;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import com.amt.authentication.CheckCredentials;
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
        if (CheckCredentials.isAdmin(request)) {
            //We need both of these to display the admin menu with all categories and articles
            request.setAttribute(ARTICLES, ArticleOps.fetchAll());
            request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
            RequestDispatcher rd = request.getRequestDispatcher("shopManagement.jsp");
            rd.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "home");
        }
    }

    /**
     * This doPost function only handles 1 case : when we want to add a category to an article from the shopManagement page
     * @param request the request received
     * @param response the response that will be sent
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("articleId")!= null && request.getParameter("categoryId") != null){
            try{
                Integer articleId = Integer.valueOf(request.getParameter("articleId"));
                Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
                Article article = ArticleOps.fetchOne(articleId);
                Category category = CategoryOps.fetchOne(categoryId);
                Boolean articleHasCategory = false;
                //checks that the article doesn't already have that category
                for(Category c : article.getCategories()){
                    articleHasCategory = article.hasCategory(c);
                }
                if(!articleHasCategory){
                    ArticleOps.addCategory(article.getId(), category.getId());
                }
                response.sendRedirect(request.getContextPath() + "/shopManagement");
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/shopManagement");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/shopManagement");
        }
    }
}
