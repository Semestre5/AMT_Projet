package com.amt.product;

import com.amt.object.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/shop/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        url = (String) request.getRequestURL().toString();
        url = url == null ? request.getRequestURI() : url;
        int index = url.indexOf("/shop/");
        String productIdStr = url.substring(index + "/shop/".length());
        int productId = 0;
        try {
            productId = Integer.parseInt(productIdStr);
        } catch (Exception e){}

        request.setAttribute("productDetails", productId == 1 ? Product.TEST_PRODUCT1: Product.TEST_PRODUCT2);
        RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
