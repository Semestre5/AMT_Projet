package com.amt.shopManagement;



import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CategoryAddServlet", value = "/categoryAdd")
public class CategoryAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("categoryAdd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer id = CategoryOps.isStored(name);
        if(id == null){
            // we try to upload only when we are sure the other parameter are ok
            Category newCategory = new Category(name);
            CategoryOps.addCategory(newCategory);
            //TODO do I redirect ? probably not -> add redirect button somewhere on the page
            response.sendRedirect(request.getContextPath() + "/shopManagement");
        }else{
            request.setAttribute("duplicatedName", name);
            request.setAttribute("duplicatedID", id);
            RequestDispatcher rd = request.getRequestDispatcher("articleAdd.jsp");
            rd.forward(request, response);
        }
    }
}
