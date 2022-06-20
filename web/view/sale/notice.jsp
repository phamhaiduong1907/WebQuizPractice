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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sale/notice.css">

    </head>

    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

    <body>
        <div class="message__box">
            <p>${requestScope.mess}</p>
            <p>Thank you for your patience!</p>
            <p>You may close this window now.</p>
        </div>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <footer>
            FOOTER
        </footer>
    </body>
</html>
