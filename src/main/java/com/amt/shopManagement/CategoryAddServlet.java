package com.amt.shopManagement;



import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoryAddServlet", value = "/categoryAdd")
public class CategoryAddServlet extends HttpServlet {
    public static final String CATEGORIES = "categories";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(CATEGORIES, CategoryOps.fetchAll());
        RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer id = CategoryOps.isStored(name);
        if(id == null){
            //TODO need to update this with new registerCategory

            // we try to upload only when we are sure the other parameter are ok
            //Category newCategory = new Category(name);
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
