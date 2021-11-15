package com.amt.product;

import com.amt.object.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/shop/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        url = (String) request.getRequestURL().toString();
        url = url == null ? request.getRequestURI() : url;
        int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

        request.setAttribute("productDetails", productId == 1 ? Product.TEST_PRODUCT1: Product.TEST_PRODUCT2);
        RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
        rd.forward(request, response);
        System.out.print("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
