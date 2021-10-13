<%@ page import="com.example.template.HelloServletModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    HelloServletModel test = (HelloServletModel) request.getAttribute("returnName");
    out.print("Hello " + test.getName());
%>
</body>
</html>
