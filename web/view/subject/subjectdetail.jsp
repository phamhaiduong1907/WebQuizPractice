
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : coursedetails
    Created on : May 30, 2022, 9:39:27 AM
    Author     : long
--%>

<%@page import="model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subject Name</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/subject/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/subject/detail.css">

    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <div class="heading">
        </div>


        <div class="main">

            <!-- LEFT  -->
            <section class="subject__list">

                <div class="subject__item">
                    <div class="subject__thumbnail">
                        <img src="images/thumbnails/${requestScope.course.thumbnailUrl}" alt=""/>
                    </div>
                    <div class="subject__content">
                        <label>${requestScope.course.courseName}</label>
                        <div class="subject__info">
                            <p><i class="fa fa-align-justify"></i> Category: ${requestScope.course.subcategory.subcategoryName}</p>
                        </div>
                        <div class="subject__review">
                            <p>&emsp;&emsp;${requestScope.course.briefInfo}</p>
                        </div>
                        <div class="subject__description">
                            <p>&emsp;&emsp;${requestScope.course.description}</p>

                        </div>

                        <div class="backlink__container">

                            <div class="subject__detail">

                                <div>
                                    <p>${requestScope.course.tagline}</p>
                                </div>
                                <p>Start at ${requestScope.course.pricePackages[0].priceName} only for $${requestScope.course.pricePackages[0].listPrice}</p>

                                <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                            </div>
                            <div class="subject__register">
                                <button onclick="openPopup()" id="register">Register</button>
                            </div>

                        </div>
                    </div>
                </div>
            </section>


            <!-- RIGHT -->
            <jsp:include page="${pageContext.request.contextPath}../../view/searchsider.jsp"/>
        </div>
        <!-- POPUP REGISTER -->
    </div>
    <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
    <script src="${pageContext.request.contextPath}/js/registerPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
    crossorigin="anonymous"></script>
    <script src="js/common/home.js"></script>
    <script src="js/register.js"></script>
    <script>
                                    $(document).ready(function () {
        <c:choose>
            <c:when test="${param.registerSucessfully == 'true'}">
                                        alert("Register successfully");
            </c:when>
            <c:when test="${param.registerSucessfully == 'registered'}">
                                        alert("You already registered this course with the same unregistered Gmail");
            </c:when>
            <c:when test="${param.registerSucessfully == 'duplicateGmail'}">
                                        alert("Please log in to register");
            </c:when>
            <c:when test="${param.registerSucessfully == 'false'}">
                                        alert("Can't register right now, please try again");
            </c:when>
            <c:when test="${param.registerSucessfully == 'duplicateGmailLoggedIn'}">
                                        alert("You already registered this course");
            </c:when>



        </c:choose>
                                    });

        <c:if test="${sessionScope.account != null && requestScope.course.isRegistered == true}">
                                    disableButton("register");
        </c:if>




    </script>     

</body>

</html>
