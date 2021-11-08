package com.example.template;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "queryServlet", value = "/queryServlet")
public class QueryServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        // Get a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Query Response</title></head>");
        out.println("<body>");
        try {
                // Step 1: Allocate a database 'Connection' object
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/dummydb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "toor");   // For MySQL

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();

                 // Step 3: Execute a SQL SELECT query
                     String sqlStr = "select * from users";

                    ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
                 // Step 4: Process the query result set
                    int count = 0;
                    while(rset.next()) {
                        // Print a paragraph <p>...</p> for each record
                        out.println("<p>" + rset.getString("id")
                                + ", " + rset.getString("username")
                                + ", " + rset.getString("address") + "</p>");
                        count++;
                      }
                   out.println("<p>==== " + count + " records found =====</p>");
                 } catch(Exception ex) {
            out.println( "<p>Error: " + ex.getMessage() + "</p>" );
            out.println( "<p>Check Tomcat console for details.</p>" );
            ex.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
