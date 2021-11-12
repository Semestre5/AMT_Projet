package com.amt.shop;

import com.amt.object.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {

    private static final List<String> TEST_CATEGORIES = Arrays.asList("Cat1", "Cat2", "Cat3");
    public static final String CATEGORY_ATTR = "categories";
    public static final String PRODUCTS_ATTR = "products";

    public ShopServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = new ArrayList<>();
        // -------- Adding products manualy for testing----------
        // Modify with ORM when available
        products.add(Product.TEST_PRODUCT1);
        products.add(Product.TEST_PRODUCT2);
        products.add(Product.TEST_PRODUCT2);
        products.add(Product.TEST_PRODUCT2);
        products.add(Product.TEST_PRODUCT1);
        request.setAttribute(PRODUCTS_ATTR, products);
        request.setAttribute(CATEGORY_ATTR, TEST_CATEGORIES);
        RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}
