package com.amt.shop;

import com.DAO.Objects.Article;
import com.DAO.Objects.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    private final List<Category> TEST_CATEGORIES = new ArrayList<>();
    private List<Article> articles = Arrays.asList(Article.TEST_ARTICLE1, Article.TEST_ARTICLE2);
    public static final String CATEGORY_ATTR = "categories";
    public static final String ARTICLES_ATTR = "articles";

    public ShopServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(TEST_CATEGORIES.isEmpty()) {
            for(int i = 0; i<4; ++i) {
                Category c = new Category("cat" + Integer.toString(i));
                c.setId(i);
                TEST_CATEGORIES.add(c);
            }

        }
        // -------- Adding products manualy for testing----------
        // Modify with ORM when available
        int i = 1;
        for (Article a: this.articles){
            a.setId(i);
            a.setCategoriesID(List.of(i++));
        }

        request.setAttribute(ARTICLES_ATTR, articles);
        request.setAttribute(CATEGORY_ATTR, TEST_CATEGORIES);
        RequestDispatcher rd = request.getRequestDispatcher("/shop.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String[] catStr = request.getParameterValues("category");
        List <Article>articlesToDisplay = new ArrayList();
        if (catStr != null) {
            for (String s : catStr)
                for (Article a : this.articles)
                    if (a.getCategoriesID().contains(Integer.parseInt(s)))
                        articlesToDisplay.add(a);
        } else
            articlesToDisplay.addAll(articles);


        request.setAttribute(ARTICLES_ATTR, articlesToDisplay);
        request.setAttribute(CATEGORY_ATTR, TEST_CATEGORIES);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }



    public void destroy() {

    }
}
