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
            Set<Article> articles = new HashSet<>((Set<Article>) ArticleOps.fetchAllAsSet());
            request.setAttribute(ARTICLES_ATTR, articles);
            List<Category> categories = (List<Category>) CategoryOps.fetchAll();
            removeEmptyCategories(categories);
            request.setAttribute(CATEGORY_ATTR, categories);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CheckCredentials.isAdmin(request)) {
            response.setContentType("text/html");

            Integer[] catIds = toIntegerArray(request.getParameterValues("category"));
            Category test = CategoryOps.fetchOne(1);
            List<Category> categories = CategoryOps.fetchAll();
            removeEmptyCategories(categories);
            request.setAttribute(CATEGORY_ATTR, categories);
            System.out.print(catIds.length);
            List<Category> articleCategoryToFetch = CategoryOps.fetchAllByIdList(catIds);
            request.setAttribute(ARTICLES_ATTR, catIds.length == 0 ? ArticleOps.fetchAllAsSet()
                    : ArticleOps.fetchDistinctByCategoryList(articleCategoryToFetch));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "shopManagement");
        }
    }
    private void removeEmptyCategories(List<Category> categoryList){
        categoryList.removeIf(c -> (c.getArticles().isEmpty()));
    }

    private static Integer[] toIntegerArray(String[] strArr){
        if (strArr == null)
            return new Integer[0];
        Integer[] intArr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; ++i)
            intArr[i] = Integer.parseInt(strArr[i]);
        return intArr;
    }

    public static void main(String [] args) {
        String[] str = {"1", "2", "3"};
        System.out.print(toIntegerArray(str).length);
    }


}
