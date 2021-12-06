package com.amt.authentication;

import com.DAO.Access.UserOps;
import com.DAO.Objects.User;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("idUser") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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

            JSONObject resultLogin = CheckCredentials.checkCredentials(username, password, CheckCredentials.loginPath);

            if(resultLogin.getInt("code") == 200) {
                JSONObject account = resultLogin.getJSONObject("account");
                Integer idUser = account.getInt("id");
                String roleUser = account.getString("role");

                // Test to add the user in DB
                try {
                    User u = new User();
                    u.setId(idUser);
                    UserOps.register(u);
                    // If user already exist
                } catch (Exception e) {
                    System.out.println("User already exist");
                }

                session = request.getSession();
                session.setAttribute("idUser", idUser);
                session.setAttribute("roleUser", roleUser);

                if (roleUser.equals("admin")) {
                    response.sendRedirect("shopManagement");
                } else {
                    response.sendRedirect("home");
                }
            } else {
                request.setAttribute("statusCode", resultLogin.getInt("code"));
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Location", "home");
        }
    }
}
