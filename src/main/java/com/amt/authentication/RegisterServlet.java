package com.amt.authentication;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.*;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("idUser") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Location", "home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("idUser") == null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Check if the username and password has been correctly putted
            if (username == null || password == null || username.equals("") || password.equals("")) {
                request.setAttribute("errorMessage", "Something went wrong, please try again");
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }

            JSONObject resultRegister = CheckCredentials.checkCredentials(username, password, CheckCredentials.registerPath);

            if(resultRegister.getInt("code") == 201) {
                request.setAttribute("successMessage", "The user has been created, you can now log in to your account");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                if (resultRegister.getInt("code") == 409) {
                    request.setAttribute("errorMessage", "The username already exists");
                } else if (resultRegister.getInt("code") == 422) {
                    request.setAttribute("errorMessage", "The data you entered are not valid, maybe the password is not strong enough");
                }

                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Location", "home");
        }
    }
}
