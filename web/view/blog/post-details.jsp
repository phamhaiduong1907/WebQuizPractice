<%-- 
    Document   : post-details
    Created on : May 30, 2022, 8:38:19 AM
    Author     : long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog Detail</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/list.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/detail.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <div class="main">
            <section class="left__side">
                <div class="blog__wrapper">
                    <div class="blog__thumbnail">
                        <img src="images/blog/${requestScope.post.thumbnailUrl}" alt=""/>
                    </div>
                    <div class="blog__short-description">
                        <h2 class="blog__title">${requestScope.post.title}</h2>
                        <div class="blog__brief-info">
                            <p>
                                <i class="fa-solid fa-calendar-days"></i> ${requestScope.post.updatedDate}
                            </p>
                            <p>
                                <i class="fa-solid fa-pen-nib"></i> ${requestScope.post.author.username}
                            </p>
                            <p>
                                <i class="fa-solid fa-folder"></i> ${requestScope.post.subcategory.subcategoryName}
                            </p>
                        </div>
                        <div class="blog__preview">
                            <p>
                                ${requestScope.post.briefInfo}
                            </p>
                        </div>
                    </div>
                    <div class="blog__content">
                        <p>
                            ${requestScope.post.description}
                        </p>
                    </div>
                </div>
            </section>

            <!-- RIGHT -->
            <jsp:include page="${pageContext.request.contextPath}../../view/searchsider.jsp"/>
        </div>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>


    </body>

</html>
