package com.amt.shop;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CatergoryOps;
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
    private final List<Article> articles = Arrays.asList(Article.TEST_ARTICLE1, Article.TEST_ARTICLE2);
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
        request.setAttribute(ARTICLES_ATTR, ArticleOps.fetchAll());
        request.setAttribute(CATEGORY_ATTR, TEST_CATEGORIES);
        RequestDispatcher rd = request.getRequestDispatcher("/shop.jsp");
        rd.forward(request, response);
    }

    /**
     * Used to sort the website
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List <Article>articlesToDisplay = ArticleOps.fetchAll(); //TODO add fetchArticleWCat
        /*
        if (catStr != null) {
            for (String s : catStr)
                for (Article a : this.articles)
                    if (a.getCategoriesID().contains(Integer.parseInt(s)))
                        articlesToDisplay.add(a);
        } else
            articlesToDisplay.addAll(articles);
        */
        request.setAttribute(ARTICLES_ATTR, articlesToDisplay);
        request.setAttribute(CATEGORY_ATTR, CatergoryOps.fetchAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {

    }
}
