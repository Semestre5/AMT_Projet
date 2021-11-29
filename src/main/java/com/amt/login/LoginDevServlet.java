package com.amt.login;

import com.DAO.Access.UserOps;
import com.DAO.Objects.User;

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

        // Test to add the user in DB
        try {
            User u = new User();
            u.setId(idUser);
            UserOps.register(u);
            // If user already exist
        } catch (Exception e) {
            System.out.println("User already exist");
        }

        HttpSession session = request.getSession();
        session.setAttribute("idUser", idUser);
        session.setAttribute("roleUser", roleUser);

        response.sendRedirect(".");
    }
}
