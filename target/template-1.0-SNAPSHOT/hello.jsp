<%@ page import="com.example.template.HelloServletModel" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    HelloServletModel test = (HelloServletModel) request.getAttribute("returnName");
    out.print("<h1>Hello " + test.getName() + "!</h1>");
%>
</body>
</html>
