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
        String name = request.getParameter("name");
        Map<String, String[]> parameterName = request.getParameterMap();
        Integer id = CategoryOps.isStored(name);
        if(parameterName.containsKey("id")){
            int idCategory = Integer.parseInt(request.getParameter("id"));
            Category category = CategoryOps.fetchOne(idCategory);

            // we check if the category has articles linked to it
            Boolean noArticles = category.getArticles().isEmpty();

            if(noArticles){
                CategoryOps.deleteCategory(idCategory);
                response.sendRedirect(request.getContextPath() + "/categoryAdd");
            } else {
                if(parameterName.containsKey(NEED_CONFIRMATION)){

                }else{
                    request.setAttribute(NEED_CONFIRMATION, true);
                    request.setAttribute(ARTICLES_CONCERNED, category.getArticles());
                }


            }
        }else{
            if(id == null){
                CategoryOps.addCategory(name);
                response.sendRedirect(request.getContextPath() + "/categoryAdd");
            }else{
                request.setAttribute("duplicatedName", name);
                //TODO look at if there's another way to handle that (the request is not done otherwise)
                request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
                RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
                rd.forward(request, response);
            }
        }
    }
}
