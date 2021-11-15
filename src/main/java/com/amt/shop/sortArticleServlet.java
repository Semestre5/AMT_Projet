package com.amt.shop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "sortArticleServlet", value = "/sortArticle")
public class sortArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("hello");
        response.setContentType("text/html");
        String[] catStr = request.getParameterValues("category");
        List<Integer> catIds = new ArrayList<>();
        if (catStr != null)
            for (String s : catStr) catIds.add(Integer.parseInt(s));
        request.setAttribute("catToDisplay", catIds);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }
}
