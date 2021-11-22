package com.amt.cart;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartServletModel cart = session(request);
        request.setAttribute("cart", cart);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        if (!cart.isMember())
            request.getSession().setAttribute("cart", cart);
        rd.forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartServletModel cart = session(request);
        Map<String, String[]> parameterName = request.getParameterMap();
        // we have an update, add or unique supression of an Article
        if (parameterName.containsKey("id") && parameterName.containsKey("quantity")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.update(id, quantity);
        }
        // suppression of all the cart
        else if (parameterName.containsKey("delete")){
            cart.deleteAll();
        }
        if (!cart.isMember())
            request.getSession(false).setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    private CartServletModel session(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // we are online, so we reset our data every actions
        if (session != null && session.getAttribute("idUserSession") != null ) {
            return new CartServletModel(request, true);
        }
        // we have a Cart in cache
        if (session != null && session.getAttribute("cart")!= null){
            return ((CartServletModel) session.getAttribute("cart"));
        }
        // we create a Cart for session
        return new CartServletModel(request, false);
    }
}