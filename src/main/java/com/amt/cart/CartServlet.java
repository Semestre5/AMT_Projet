package com.amt.cart;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
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
        CartModel cart = retrieveCart(request);
        request.setAttribute("cart", cart);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CartModel cart = retrieveCart(request);
        Map<String, String[]> parameterName = request.getParameterMap();

        // we have an update, add or unique suppression of an Article
        if (parameterName.containsKey("id") && parameterName.containsKey("quantity")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Article article = ArticleOps.fetchOne(id);

            // if the requested modification of cart claimed is invalid, we throw it.
            if (article == null || !article.isSellable()){
                throw new Exception("The article is not available");
            }
            // we verify if article is in retrieved Cart
            Boolean inCart = cart.has(id);

            if (!inCart && quantity > 0)
                cart.add(id,quantity);
            else if (inCart && quantity <= 0){
                cart.delete(id);
            }
            else if (inCart && parameterName.containsKey("incremental")){
                cart.update_incremental(id, quantity);
            }
            else if (inCart){
                cart.update(id, quantity);
            }
            else{
                throw new Exception("Please select a valid quantity");
            }
        }
        // suppression of all the cart
        else if (parameterName.containsKey("delete")){
            cart.deleteAll();
        }
        // we update the session if the user is not connected with an account
        if (cart.getIdSession() == null)
            request.getSession(false).setAttribute("cart", cart);

        response.sendRedirect("cart");
    }

    private CartModel retrieveCart(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // we are online, so we reset our data every actions
        if (session != null && session.getAttribute("idUser") != null ) {
            return new CartModel((Integer) session.getAttribute("idUser"));
        }
        // we have a Cart in cache
        if (session != null && session.getAttribute("cart")!= null){
            return ((CartModel) session.getAttribute("cart"));
        }
        // we create a Cart for offline client
        return new CartModel(null);
    }
}