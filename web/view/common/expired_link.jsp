<%-- 
    Document   : resetting_page
    Created on : May 26, 2022, 9:27:54 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resetting page</title>
        <link href="css/common/resseting_page.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="form">
            <div class="messageBox">

                <p class="expired__message">${requestScope.messageExpired}</p>


            </div>

        </div>



        <script src="js/common/resetting_page.js" type="text/javascript"></script>
    </body>

</html>
