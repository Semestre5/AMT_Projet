package com.amt.authentication;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CheckCredentials {
    public static final String loginPath = "auth/login";
    public static final String registerPath = "accounts/register";
    public static final String localhost = "http://localhost:8091/";
    public static final String server = "http://10.0.1.92:8080/";

    public static JSONObject checkCredentials(String username, String password, String path) throws IOException {
        // Création de la requête HTTP
        URL url = new URL(localhost + path);
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

        JSONObject response = new JSONObject();

        // Récupération du contenu de la réponse
        if(status >= 200 && status <= 299){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            response = new JSONObject(content.toString());
        }

        // Création d'un JSON pour la réponse
        response.put("code", status);

        return response;
    }

    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String roleUser = (String) session.getAttribute("roleUser");
        return roleUser != null && roleUser.equals("admin");
    }
}
