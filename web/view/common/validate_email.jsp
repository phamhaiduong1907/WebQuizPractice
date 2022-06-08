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
        <title>Confirm register</title>
        <link href="css/common/resseting_page.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="form">
            <form action="registernext" method="POST">
                <table class="input__table">
                    <tr>
                        <td>Check your registered email and enter your confirmation code here:</td>
                        <td><input type="text" name="confirmCode" id="confirmCode" class="confirmCode"></td>
                    </tr>
                </table>
                <div class="button_wrapper">
                    <input type="submit" id="resetButton">
                </div>
            </form>
        </div>

        <div class="messageBox">
            <p>${requestScope.validate_email_status}</p>
        </div>

    </body>

</html>
