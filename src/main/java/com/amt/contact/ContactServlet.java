package com.amt.contact;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ContactServlet", value = "/contact")
public class ContactServlet extends HttpServlet {

    public ContactServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("contact.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mail = request.getParameter("email");
        String message = request.getParameter("comment");

        ContactServletModel obj = new ContactServletModel();
        obj.setName(name);
        obj.setMail(mail);
        obj.setMessage(message);

        request.setAttribute("returnObject", obj);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showDetails.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
