<%-- 
    Document   : notice.jsp
    Created on : Jun 19, 2022, 7:55:52 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="font-size: 60px;">${requestScope.mess}</h1>
        <h1 style="font-size: 50px;">Thank you for your patience! <3</h1>
        <h1 style="font-size: 45px;">You may close this window now.</h1>
    </body>
</html>
