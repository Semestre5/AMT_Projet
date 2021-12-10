package com.amt.shop;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import com.amt.authentication.CheckCredentials;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    public static final String CATEGORY_ATTR = "categories";
    public static final String ARTICLES_ATTR = "articles";

    public ShopServlet() {
        super();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CheckCredentials.isAdmin(request)) {
            request.setAttribute(ARTICLES_ATTR, ArticleOps.fetchAll());
            request.setAttribute(CATEGORY_ATTR, removeEmptyCategories(CategoryOps.fetchAll()));
            RequestDispatcher rd = request.getRequestDispatcher("/shop.jsp");
            rd.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "shopManagement");
        }
    }

    /**
     * Used to sort the website
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @SneakyThrows
    @Override
    // DPE - Une fonction de plus de 10 lignes, c'est une fonction trop longue ;P
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // DPE - Je vous avais mis un commentaire sur le return early dans la précédente review ...
        if (!CheckCredentials.isAdmin(request)) {
            response.setContentType("text/html");
            String[] catIds = request.getParameterValues("category");
            List<Category> categoriesToDisplay = new ArrayList<>();
            List<Article> articlesToDisplay = new ArrayList<>();
            if (catIds != null) {
                for (String id : catIds) {
                    Category catToFetch = CategoryOps.fetchOne(Integer.parseInt(id));
                    List<Article> articlesToAdd = (List<Article>) ArticleOps.fetchAllByCategory(catToFetch);
                    articlesToDisplay.addAll(articlesToAdd);
                }
                List<Article> distinctArticle = new ArrayList<>();
                for(Article a: articlesToDisplay)
                    if (!distinctArticle.contains(a))
                        distinctArticle.add(a);
                request.setAttribute(ARTICLES_ATTR, distinctArticle);
            } else {
                request.setAttribute(ARTICLES_ATTR, ArticleOps.fetchAll());
            }
            request.setAttribute(CATEGORY_ATTR, removeEmptyCategories(CategoryOps.fetchAll()));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "shopManagement");
        }
    }
    private List<Category> removeEmptyCategories(List<Category> categoryList){
        List<Category> temp = new ArrayList<Category>();
        for(Category c : categoryList ){
            if(!c.getArticles().isEmpty()){
                temp.add(c);
            }
        }
        return temp;
    }
}
