package com.example.template;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");

        HelloServletModel obj = new HelloServletModel();
        obj.setName(name);
        request.setAttribute("returnName", obj);
        RequestDispatcher rd = request.getRequestDispatcher("hello.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}