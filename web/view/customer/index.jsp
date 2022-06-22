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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">

    </head>

    <body>
        <!-- HEADER -->
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <!-- PAGE CONTENT -->
        <section id="main">
            <section class="slider">
                <div id="slider" class="carousel carousel-dark slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#slider" data-bs-slide-to="0" class="active" aria-current="true"
                                aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#slider" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#slider" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <c:forEach items="${requestScope.sliders}" var="s">
                            <div class="carousel-item active" data-bs-interval="5000">
                                <a href="${s.backlink}">
                                    <div class="block__item">
                                        <img src="${s.imageUrl}">
                                    </div>
                                    <div class="carousel-caption d-none d-md-block">
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
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
                <h1>HOT POSTS</h1>
                <div class="post__wrapper">
                    <c:forEach items="${requestScope.posts}" var="p">
                        <div class="post__item">
                            <form action="blogdetail" method="GET" class="post__form">
                                <button type="submit" class="post__detail">
                                    <input type="hidden" value="${p.postID}" name="postID">
                                    <div class="post__short">
                                        <p>${p.updatedDate}</p>
                                    </div>
                                    <div class="post__info">
                                        <div class="post__thumbnail">
                                            <img src="images/blog/${p.thumbnailUrl}" alt="alt"/>
                                        </div>
                                        <div class="post__title">
                                            <p>${p.title}</p>
                                        </div>
                                        <div class="post__info">
                                            <p>${p.briefInfo}</p>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        </div>
                    </c:forEach>

                </div>
            </section>

            <section class="subject">
                <h1>POPULAR COURSES</h1>
                <button class="pre-btn"><img src="images/arrow.png" alt=""></button>
                <button class="nxt-btn"><img src="images/arrow.png" alt=""></button>
                <div class="subject__container">
                    <div class="subject__content">
                        <c:forEach items="${requestScope.courses}" var="c">

                            <div class="subject__card">
                                <div class="subject__card-content">
                                    <div class="subject__thumnail post__thumbnail">
                                        <img src="images/thumbnails/${c.thumbnailUrl}">
                                    </div>

                                    <div class="subject__title post__title">
                                        <a href="subjectdetail?subjectID=${c.courseID}"><p>${c.courseName}</p></a>


                                    </div>
                                    <div class="subject__description">
                                        <p>${c.briefInfo}</p>
                                    </div>
                                </div>
                                <div class="course__detail">
                                </div>  

                            </div>


                        </c:forEach>

                    </div>
                </div>
            </section>



            <section class="popup" style="display: <c:choose>
                         <c:when test="${sessionScope.profile_status != null || sessionScope.changepass_status != null}">
                             <%="flex; "%>
                         </c:when>
                         <c:otherwise>
                             <%="none;"%>
                         </c:otherwise>
                     </c:choose>">
                <div class="popup__content">
                    <img src="images/close.png" alt="" class="close">

                    <div class="form_user-profile" style="display: <c:choose>
                             <c:when test="${sessionScope.profile_status != null}">
                                 <%="block; "%>
                             </c:when>
                             <c:otherwise>
                                 <%="none;"%>
                             </c:otherwise>
                         </c:choose>">
                        <h2>User Profile</h2>
                        <form action="profile" method="POST" enctype="multipart/form-data">

                            <div class="user__avatar">
                                <c:choose>
                                    <c:when test="${sessionScope.user.profilePictureUrl != 'none'}">
                                        <img src="<c:out value="${sessionScope.user.profilePictureUrl}"/>" id="photo">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="images/profile/default.jpg" id="photo">
                                    </c:otherwise>
                                </c:choose>
                                <input type="file" name="profilePicture" id="profilePicture" onchange="return fileValidation()" oninvalid="this.setCustomValidity('Please select a picture!')" oninput="this.setCustomValidity('')">
                                <label for="profilePicture" title="Please update your picture!" id="uploadBtn" style="top: 35%">Choose Photo</label>
                            </div>
                            <input type="text" name="email" id="email" value="<c:out value="${sessionScope.user.account.username}"/>" disabled placeholder="Your email">
                            <input type="text" name="firstName" id="firstName" value="<c:out value="${sessionScope.user.firstName}"/>" title="Your name can't be empty" placeholder="Enter your first name" required>
                            <input type="text" name="lastName" id="lastName" value="<c:out value="${sessionScope.user.lastName}"/>" title="Your name can't be empty" placeholder="Enter your last name" required>
                            <input type="text" name="phone" id="phone" value="<c:out value="${sessionScope.user.phoneNumber}"/>" pattern="[0-9]{9,10}" title="Please enter a valid phone number (9-10 number with no separator)" placeholder="Enter your phone" required>
                            <div class="profile__gender signup__gender">
                                <h5>Gender</h5>

                                <input type="radio" name="gender" value="male" <c:if test="${sessionScope.user.gender}">
                                       checked="checked"
                                    </c:if> id="" required> <p>Male</p>

                                <input type="radio" name="gender" value="female" <c:if test="${!sessionScope.user.gender}">
                                       checked="checked"
                                    </c:if> id="" required> <p>Female</p>

                            </div>
                            <input type="text" name="address" id="address" title="Your address can't be empty" value="<c:out value="${sessionScope.user.address}"/>" placeholder="Enter your address" required>
                            <div class="form__button">
                                <button type="submit">Save</button>
                            </div>
                        </form>
                        <div class="message__box">
                            <p>${sessionScope.profile_status}</p>
                            <c:remove var="profile_status" scope="session"/>
                        </div>
                    </div>

                    <div class="form__change-password" style="display: <c:choose>
                             <c:when test="${sessionScope.changepass_status != null}">
                                 <%="block; "%>
                             </c:when>
                             <c:otherwise>
                                 <%="none;"%>
                             </c:otherwise>
                         </c:choose>">
                        <h2>Change Password</h2>
                        <form action="changepass" method="POST" onsubmit="return checkOldNewPass()">
                            Enter current password: <input type="password" name="currentPassword" id="currentPassword" required placeholder="Enter your current password">
                            Enter new password: <input type="password" name="newPassword" id="newPassword" onchange="checkPassword()" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Must be at minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character" placeholder="Enter new password">
                            Confirm new password: <input type="password" name="confirmNewPassword" id="confirmNewPassword" required placeholder="Reenter your new password">
                            <div class="form__button">
                                <button type="submit">Save</button>
                            </div>
                        </form>
                        <div class="message__box">
                            <p>${sessionScope.changepass_status}</p>
                            <c:remove var="changepass_status" scope="session"/>
                        </div>
                    </div>
                </div>

            </section>



            <footer>
                <p>COPYRIGHT</p>
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
            <script src="${pageContext.request.contextPath}/js/script.js"></script>
            <script src="${pageContext.request.contextPath}/js/swiper.js"></script>
            <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
            <script src="${pageContext.request.contextPath}/js/register.js"></script>
            <script src="${pageContext.request.contextPath}/js/profile.js"></script>
            <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
            <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
            <script src="${pageContext.request.contextPath}/js/unauthorizedAccess.js"></script>
    </body>

</html>