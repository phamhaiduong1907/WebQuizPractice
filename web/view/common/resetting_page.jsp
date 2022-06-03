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
            <form method="POST" action="forgotPasswordNext">
                <table>
                    <tr>
                        <td>New password:</td>
                        <td><input type="password" name="password" id="password" onkeyup='check();'>
                            <input type="hidden" value="${param.token}" name="token" id="token">
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm password:</td>
                        <td><input type="password" name="confirmPassword" id="confirmPassword" onkeyup='check();'></td>
                        <td>  <span id='matchingMessage'></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" id="resetButton"></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>

                </table>
            </form>

        </div>

        <div class="messageBox">
            
            <p class="success__message">${requestScope.messageUpdate}</p>
            <p class="failed__message">${requestScope.messageExpired}</p>
            <p class="failed__message">${requestScope.messageFailMatchPassword}</p>
            <p class="failed__message" id="failed__message" >${requestScope.messageFailRegexPassword}</p>


        </div>

        <script src="js/common/resetting_page.js" type="text/javascript"></script>
    </body>

</html>
