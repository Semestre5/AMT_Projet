package com.amt.shop;

import com.DAO.Objects.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {

    private static final List<String> TEST_CATEGORIES = Arrays.asList("Cat1", "Cat2", "Cat3");
    public static final String CATEGORY_ATTR = "categories";
    public static final String ARTICLES_ATTR = "articles";

    public ShopServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = new ArrayList<>();
        // -------- Adding products manualy for testing----------
        // Modify with ORM when available
        Article.TEST_ARTICLE1.setId(1);
        Article.TEST_ARTICLE2.setId(2);
        articles.add(Article.TEST_ARTICLE1);
        articles.add(Article.TEST_ARTICLE2);
        articles.add(Article.TEST_ARTICLE1);
        articles.add(Article.TEST_ARTICLE2);
        request.setAttribute(ARTICLES_ATTR, articles);
        request.setAttribute(CATEGORY_ATTR, TEST_CATEGORIES);
        RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}
