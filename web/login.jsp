<%-- 
    Document   : login
    Created on : May 27, 2022, 12:57:51 PM
    Author     : Hai Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username:<input type="text" name="username"/><br>
            Password:<input type="text" name="password"/><br>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
