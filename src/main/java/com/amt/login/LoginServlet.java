package com.amt.login;

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

        if(checkLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("idUserSession", 0);

            response.sendRedirect(".");
        } else {
            response.sendRedirect("login");
        }
    }

    private boolean checkLogin(String username, String password) throws IOException {
        URL url = new URL("http://localhost:8090/auth/login");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestMethod("POST");

        JSONObject cred = new JSONObject();
        cred.put("username", username);
        cred.put("password", password);

        OutputStream os = connection.getOutputStream();
        os.write(cred.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();

        int status = connection.getResponseCode();
        if(status == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // Traiter le texte retourné pour ouvrir la session

            return true;
        }

        return false;
    }
}
