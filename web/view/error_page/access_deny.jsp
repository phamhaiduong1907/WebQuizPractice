<%-- 
    Document   : access_deny
    Created on : May 26, 2022, 5:43:46 PM
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
        
        
        <script>
            var result = confirm("You are not allowed to reach this page!\nCome back previous page?");
            if(result){
                window.history.back();
            }
        </script>
    </body>
</html>
