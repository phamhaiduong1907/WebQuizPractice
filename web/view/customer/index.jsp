<%@page import="model.Slider"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
        <%
            ArrayList<Slider> sliders = (ArrayList<Slider>) request.getAttribute("sliders");
        %>
    </head>

    <body>
        <!-- HEADER -->
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <!-- PAGE CONTENT -->
        <section id="main">
            <section class="slider">
                <div id="slider" class="carousel carousel-dark slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <%for (int i = 0; i < sliders.size(); i++) {%>
                        <button type="button" data-bs-target="#slider" data-bs-slide-to="<%=(i)%>" <%if (i == 0) {%>
                                class="active" aria-current="true"
                                <%}%>></button>
                        <%}%>
                    </div>
                    <div class="carousel-inner">
                        <%for (int i = 0; i < sliders.size(); i++) {%>
                        <div class="carousel-item <%if (i == 0) {%> active <%}%>" data-bs-interval="5000">
                            <a href="<%=(sliders.get(i).getBacklink())%>">
                                <div class="block__item">
                                    <img src="<%=(sliders.get(i).getImageUrl())%>">
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                </div>
                            </a>
                        </div>
                        <%}%>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#slider" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#slider" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </section>

            <section class="post">
                <div class="post__heading">
                    <h1>Our Posts</h1>
                    <p class="post__explore">Explore our hot posts</p>
                </div>
                <div class="post__wrapper">
                    <c:forEach items="${requestScope.posts}" var="p">
                        <div class="post__item">
                            <a href="blogdetail?postID=${p.postID}" class="post__form">
                                <div class="post__detail">
                                    <div class="post__info">
                                        <div class="post__thumbnail">
                                            <img src="images/blog/${p.thumbnailUrl}" alt="alt"/>
                                            <div class="post__short">
                                                <p><i class="fa fa-calendar-alt"></i>${p.updatedDate}</p>
                                                <p><i class="fa fa-bars"></i>${p.subcategory.subcategoryName}</p>
                                            </div>
                                        </div>
                                        <div class="post__content">
                                            <div class="post__title">
                                                <p>${p.title}</p>
                                            </div>
                                            <div class="post__info">
                                                <p>${p.briefInfo}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>

                </div>
            </section>

            <section class="subject">
                <h1>Our Courses</h1>
                <p style="margin-left: 120px; color: #bbb;">Explore Our Most Poppular Subjects</p>
                <button class="pre-btn"><img src="images/arrow.png" alt=""></button>
                <button class="nxt-btn"><img src="images/arrow.png" alt=""></button>
                <div class="subject__container">
                    <div class="subject__content">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <div class="subject__card">
                                <a href="subjectdetail?subjectID=${c.courseID}">
                                    <div class="subject__card-content">
                                        <div class="subject__thumnail post__thumbnail">
                                            <img src="images/thumbnails/${c.thumbnailUrl}">
                                        </div>

                                        <div class="subject__details">
                                            <div class="subject__title post__title">
                                                <p>${c.courseName}</p>
                                            </div>
                                            <div class="subject__description">
                                                <p>${c.briefInfo}</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
            </section>
            <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
            <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
            <!--<script src="${pageContext.request.contextPath}/js/script.js"></script>-->
            <script src="${pageContext.request.contextPath}/js/swiper.js"></script>
            <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
            <script src="${pageContext.request.contextPath}/js/register.js"></script>
            <script src="${pageContext.request.contextPath}/js/profile.js"></script>
            <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
            <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
            <script src="${pageContext.request.contextPath}/js/unauthorizedAccess.js"></script>
    </body>

</html>