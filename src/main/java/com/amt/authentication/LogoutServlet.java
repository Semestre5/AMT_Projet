package com.amt.authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("idUser") != null) {
            session.removeAttribute("idUser");
            session.removeAttribute("roleUser");
            response.sendRedirect("home");
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Location", "home");
        }
    }
}
