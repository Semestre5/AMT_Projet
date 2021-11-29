package com.amt.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "LoginDevServlet", value = "/logindev")
public class LoginDevServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("logindev.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idUser = Integer.parseInt(request.getParameter("idUser"));
        String roleUser = request.getParameter("selectRole");

        HttpSession session = request.getSession();
        session.setAttribute("idUser", idUser);
        session.setAttribute("roleUser", roleUser);

        response.sendRedirect(".");
    }
}
