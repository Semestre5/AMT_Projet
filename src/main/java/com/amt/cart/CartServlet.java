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
        response.sendRedirect("cart");
    }

    private CartServletModel session(HttpServletRequest request){
        return new CartServletModel(request);
        //TODO if offline, return another CartServletModel
    }
}