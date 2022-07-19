<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subject List</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/subject/list.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <div class="heading">
            <h1>SUBJECT LIST</h1>
        </div>

        <div class="main">

            <!-- LEFT  -->  
            <section class="subject__list">
                <c:forEach items="${requestScope.courses}" var="c">

                    <div class="subject__item">
                        <div class="subject__thumbnail"><img style="width: 100%;height: 100%;" src="images/thumbnails/${c.thumbnailUrl}" alt="alt"/></div>
                        <div class="subject__content">
                            <h3>${c.courseName} </h3>
                            <div class="subject__info">
                                <p><i class="fa fa-align-justify"></i> Category: ${c.subcategory.subcategoryName}</p>
                                <p><i class="fa fa-calendar-alt"></i> Post on: ${c.updatedDate}</p>
                            </div>
                            <p class="subject__briefInfo">${c.briefInfo}</p>
                            <div class="backlink__container">
                                <div class="subject__detail">
                                    <a href="subjectdetail?subjectID=${c.courseID}">More Details</a>
                                </div>
                                <div class="subject__register">
                                    <button onclick="openPopup(${c.courseID})" value="${c.courseID}" id="${c.courseID}button">Register</button>


                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="register__popup" id="${c.courseID}">
                        <div class="register__form">
                            <div class="exit__button">
                                <button onclick="closePopup(${c.courseID})">
                                    <i class=" fa fa-times-circle" aria-hidden="true"></i>
                                </button>
                            </div>
                            <div class="subject__name">${c.courseName}</div>

                            <div class="subject__category">
                                <p><i class="fa fa-align-justify"></i> Category: ${c.subcategory.subcategoryName}</p>
                            </div>

                            <div class="subject__info">
                                <p style="font-size: 20px;"></p>
                            </div>
                            <form method="POST" action="courseRegistration">
                                <div class="subject__price">Price package:
                                    <select name="pricePackageID">
                                        <c:forEach items="${c.pricePackages}" var="p">
                                            <option value="${p.pricePackageID}">${p.priceName}: <c:choose>
                                                    <c:when test="${p.isOnSale}">$${p.salePrice} </c:when>
                                                    <c:otherwise>$${p.listPrice} </c:otherwise>
                                                </c:choose> </option>
                                            </c:forEach>

                                    </select>
                                </div>

                                <c:choose>
                                    <c:when test="${sessionScope.user != null}">
                                        <input type="hidden" name="firstName" value="${sessionScope.user.firstName}"><br>
                                        <input type="hidden" name="lastName"  value="${sessionScope.user.lastName}"><br>
                                        <input type="hidden" name="email" value="${sessionScope.user.account.username}"><br>
                                        <input type="hidden" name="phoneNumber" value="${sessionScope.user.phoneNumber}"><br>
                                        <input type="hidden" name="courseID" value="${c.courseID}">
                                        <input type="hidden" name="gender" value="${sessionScope.user.gender}">

                                    </c:when>
                                    <c:otherwise>
                                        <h5>Please fill in your information below</h5>

                                        <div class="user__information__form">
                                            <input type="text" name="firstName" required="required" placeholder="First name"><br>
                                            <input type="text" name="lastName" required="required" placeholder="Last name"><br>
                                            <input type="text" name="email" required="required" placeholder="Email"><br>
                                            <input type="text" name="phoneNumber" required="required" placeholder="Mobile phone"><br>
                                            <input type="hidden" name="courseID" value="${c.courseID}">

                                            <div class="user__information__gender">

                                                <label for="">Gender: </label>
                                                <input type="radio" checked="checked" name="gender" value="male">
                                                <label for="">Male</label>
                                                <input type="radio" name="gender" value="female">
                                                <label for="">Female</label>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>


                                <div class="register__confirm" onclick="openComplete()">
                                    <button>Register</button>
                                </div>
                            </form>

                        </div>
                        <!--                        <div class="register__complete" id="register__complete">
                                                    <img src="../../images/Green-Tick-Vector-PNG-Images.png" alt="">
                                                    <h4>Thank you for your registration</h4>
                                                    <button onclick="closeAllForm(${c.courseID})">Close</button>
                                                </div>-->
                    </div>
                </c:forEach>
                <div id="pagination" class="pagination"></div>
            </section>

            <!-- RIGHT -->
            <jsp:include page="${pageContext.request.contextPath}../../view/searchsider.jsp"/>
        </div>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="${pageContext.request.contextPath}/js/registerPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script src="${pageContext.request.contextPath}/js/customer/customerRegistration.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <script src="js/common/home.js"></script>
        <script src="js/register.js"></script>
        <script>

            <c:forEach items="${requestScope.courses}" var="c">
                <c:if test="${c.isRegistered == true}">
                                    disableButton("${c.courseID}button");

                </c:if>
            </c:forEach>
        </script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>
    </body>

</html>