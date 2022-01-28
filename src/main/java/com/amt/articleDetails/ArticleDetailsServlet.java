package com.amt.articleDetails;

import com.DAO.Access.ArticleOps;
import com.amt.authentication.CheckCredentials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// DPE - Maintenant que vous avez une quantité de code acceptable, il serait de temps de commencer à structurer et architecturer votre code, controllers, services, repository, exceptions.
// Une petite piste de départ : https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/shop/*")
public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CheckCredentials.isAdmin(request)) {
            try {
                String url = request.getRequestURL().toString();
                int productId = Integer.parseInt(url.substring(url.indexOf("/shop/") + "/shop/".length()));

                request.setAttribute("articleDetails", ArticleOps.fetchOne(productId));
                RequestDispatcher rd = request.getRequestDispatcher("/articleDetails.jsp");
                rd.forward(request, response);
            } catch (Exception e){
                response.sendRedirect("home");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "home");
        }
    }
}
