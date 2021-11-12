package com.amt.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartServletModel cart = new CartServletModel();

        request.setAttribute("cart", cart);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 2 add product to cart. Note : 2 cases for member and offline (products saved on the client)
        // Modification of cart here too
        /*
        if (request.getSession() != null){
            // modification in db
         }
         else{
            // TODO persistance
         }



        */

    }
}
