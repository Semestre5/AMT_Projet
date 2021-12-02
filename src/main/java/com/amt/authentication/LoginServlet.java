package com.amt.authentication;

import com.DAO.Access.UserOps;
import com.DAO.Objects.User;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            HttpSession session = request.getSession();
            session.setAttribute("idUser", idUser);
            session.setAttribute("roleUser", roleUser);

            response.sendRedirect(".");
        } else {
            request.setAttribute("statusCode", resultLogin.getInt("code"));
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
