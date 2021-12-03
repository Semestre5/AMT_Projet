package com.amt.shopManagement;



import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CategoryAddServlet", value = "/categoryAdd")
public class CategoryAddServlet extends HttpServlet {
    public static final String CATEGORIES = "categories";
    public static final String NEED_CONFIRMATION = "needConfirmation";
    public static final String ARTICLES_CONCERNED = "articlesConcerned";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
        RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> parameterName = request.getParameterMap();
        if(parameterName.containsKey("id")){
            int idCategory = Integer.parseInt(request.getParameter("id"));
            Category category = CategoryOps.fetchOne(idCategory);
            if(category.getArticles().isEmpty()){
                //TODO fix problem with deleting a category, probably backend ?
                CategoryOps.deleteCategory(idCategory);
                response.sendRedirect(request.getContextPath() + "/categoryAdd");
            } else {
                //TODO work on the deletion of a category with articles
                request.setAttribute(NEED_CONFIRMATION, category.getId());
                request.setAttribute(ARTICLES_CONCERNED, category.getArticles());
                request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
                RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
                rd.forward(request, response);
            }
        }else if (parameterName.containsKey("name")){
            String name = request.getParameter("name");
            Integer id = CategoryOps.isStored(name);
            if(id == null){
                CategoryOps.addCategory(name);
                response.sendRedirect(request.getContextPath() + "/categoryAdd");
            }else{
                request.setAttribute("duplicatedName", name);
                request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
                RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
                rd.forward(request, response);
            }
        }else if(parameterName.containsKey("delete_anyways")){
            Integer categoryId = Integer.valueOf(request.getParameter("idCategorywithArticles"));
            Category category = CategoryOps.fetchOne(categoryId);
            for (Article a: category.getArticles()){
                a.removeCategory(category);
            }
            CategoryOps.deleteCategory(categoryId);
            response.sendRedirect(request.getContextPath() + "/categoryAdd");
        }
    }
}
