package com.amt.register;

import com.DAO.Access.UserOps;
import com.DAO.Objects.User;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        JSONObject resultRegister = checkRegister(username, password);

        if(resultRegister.getString("code").equals("201")) {
            String idUser = resultRegister.getString("id");

            User u = new User();
            u.setId(Integer.parseInt(idUser));
            UserOps.register(u);

            response.sendRedirect("login");
        }
    }

    private JSONObject checkRegister(String username, String password) throws IOException {
        // Création de la requête HTTP
        URL url = new URL("http://localhost:8091/accounts/register");
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
