package com.amt.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    CartServletModel cart;

    @Override
    public void init() throws ServletException {
        cart = new CartServletModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cart", cart);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterName = request.getParameterMap();
        if (parameterName.containsKey("id") && parameterName.containsKey("quantity")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.update(id, quantity);
        }
        else if (parameterName.containsKey("delete")){
            cart.getCartProductList().clear();
        }
        response.sendRedirect("cart");
    }
}
