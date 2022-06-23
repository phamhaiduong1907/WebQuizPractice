<%-- 
    Document   : dashboard
    Created on : May 26, 2022, 11:59:22 PM
    Author     : Hai Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <section class="main">
            <!-- LEFT NAVIGATION BAR -->
         

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="right_content">

                </div>

                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    </body>
</html>
