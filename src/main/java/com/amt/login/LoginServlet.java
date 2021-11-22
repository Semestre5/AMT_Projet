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

        JSONObject resultLogin = checkLogin(username, password);

        if(resultLogin.getString("code").equals("200")) {
            JSONObject account = resultLogin.getJSONObject("account");
            String idUser = account.getString("id");
            String roleUser = account.getString("role");

            HttpSession session = request.getSession();
            session.setAttribute("idUser", idUser);
            session.setAttribute("roleUser", roleUser);

            response.sendRedirect(".");
        } else {
            response.sendRedirect("login");
        }
    }

    private JSONObject checkLogin(String username, String password) throws IOException {
        // Création de la requête HTTP
        URL url = new URL("http://localhost:8091/auth/login");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        // Création de l'objet JSON à transmettre dans la requête HTTP
        JSONObject cred = new JSONObject();
        cred.put("username", username);
        cred.put("password", password);

        // Envoi de la requête avec l'objet JSON
        OutputStream os = connection.getOutputStream();
        os.write(cred.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();

        // Récupération du code de la réponse
        int status = connection.getResponseCode();

        // Récupération du contenu de la réponse
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Création d'un JSON pour la réponse
        JSONObject response = new JSONObject(content);
        response.put("code", status);

        return response;
    }
}
