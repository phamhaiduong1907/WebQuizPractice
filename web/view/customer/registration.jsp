<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Registration</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/registration.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/subject/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/add_post.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/list.css">

    </head>

    <body>
        <!-- HEADER -->
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>



        <!-- PAGE CONTENT -->
        <section id="main">
            <div class="registration__content">


                <aside class="left">
                    <div class="registration__list">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Subject</th>
                                    <th>Registration Time</th>
                                    <th>Package</th>
                                    <th>Total cost</th>
                                    <th>Status</th>
                                    <th>Valid from</th>
                                    <th>Valid to</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <c:choose >
                                <c:when test="${requestScope.registrations.size() == 0}">
                                    <p>There are no records</p>
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                            <tbody>
                                <c:forEach items="${requestScope.registrations}" var="r">
                                    <tr>
                                        <td>${r.registrationID}</td>
                                        <td>${r.course.courseName}</td>
                                        <td>${r.registrationTime}</td>
                                        <td>${r.pricePackage.priceName}</td>
                                        <td>${r.totalCost}</td>
                                        <c:choose>
                                            <c:when test="${r.status == true}"><td class="paid">Paid</td></c:when>
                                            <c:otherwise><td class="submitted">Submitted</td> </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${r.validFrom == null && r.validTo == null}">
                                                <td>The valid from date will be updated when you paid for the course</td>
                                                <td>The valid to date will be updated when you paid for the course</td>
                                            </c:when>
                                            <c:when test="${r.validFrom != null && r.validTo == null}">
                                                <td>${r.validFrom}</td>
                                                <td>Permanent</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${r.validFrom}</td>
                                                <td>${r.validTo}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>
                                            <c:if test="${r.status == false}">
                                                <button class="edit_button" onclick="openPopup(${r.registrationID})" value="${r.course.courseID}" id="${r.course.courseID}button">Edit</button>
                                                <form method="POST" action="registrationsearch">
                                                    <input type="hidden" value="${requestScope.queryString}" name="queryString">
                                                    <input type="hidden" value="${r.registrationID}" name="registrationID">
                                                    <input class="cancel_button" type="submit " value="Cancel" onclick="return confirm('Are you sure you want to delete?')" />
                                                </form>
                                            </c:if>
                                            <c:if test="${r.status == true}">


                                            </c:if>





                                        </td>
                                    </tr>

                                <div class="subject__item">
                                    <div class="subject__content">


                                        <div class="backlink__container">

                                            <div class="subject__register">


                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Edit popup-->
                                <div class="register__popup" id="${r.registrationID}"> 
                                    <div class="register__form">
                                        <div class="exit__button">
                                            <button onclick="closePopup(${r.registrationID})">
                                                <i class=" fa fa-times-circle" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                        <div class="subject__name">${r.course.courseName}</div>

                                        <div class="subject__category">
                                            <p><i class="fa fa-align-justify"></i> Category: ${r.course.subcategory.subcategoryName}</p>
                                        </div>

                                        <div class="subject__info">
                                            <p style="font-size: 20px;"></p>
                                        </div>
                                        <form method="POST" action="updateCustomerRegistration">
                                            <div class="subject__price">Price package:
                                                <select name="pricePackageID">
                                                    <c:forEach items="${r.course.pricePackages}" var="p">
                                                        <option value="${p.pricePackageID}">${p.priceName}: <c:choose>
                                                                <c:when test="${p.isOnSale}">$${p.salePrice} </c:when>
                                                                <c:otherwise>$${p.listPrice} </c:otherwise>
                                                            </c:choose> </option>
                                                        </c:forEach>

                                                </select>
                                            </div>

                                            <c:choose>
                                                <c:when test="${sessionScope.user != null}">
                                                    <input type="hidden" value="${r.registrationID}" name="registrationID">
                                                    <input type="hidden" value="${requestScope.queryString}" name="queryString">

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
                            </tbody>

                        </table>
                    </div>
                    <div id="pagination" class="pagination"></div>

                </aside>


                <section class="option__box">
                    <div class="option__filter">
                        <div class="option__searchbar">
                            <form action="registrationsearch" method="GET">
                                <input type="text" name="search"  placeholder="Type something to search...">
                                </div>
                                <div class="option__checkbox">
                                    <h3>Category: </h3>
                                    <div class="option__options-value">
                                        <div class="accordion accordion-flush" id="accordionFlushExample">
                                            <c:forEach items="${requestScope.categories}" var="cate">
                                                <div class="accordion-item">
                                                    <h2 class="accordion-header" id="flush-headingOne">
                                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${cate.categoryID}" aria-expanded="false" aria-controls="flush-collapseOne">
                                                            ${cate.categoryName}
                                                        </button>
                                                    </h2>
                                                    <div id="flush-collapse${cate.categoryID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                                        <div class="accordion-body">
                                                            <c:forEach items="${cate.subcategories}" var="sc">
                                                                <div class="subcategory">
                                                                    <input type="checkbox" name="subcategory" value="${sc.subcategoryID}"> <span>${sc.subcategoryName}</span>
                                                                </div>
                                                            </c:forEach></div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="option__sort">
                                    <select name="sort">
                                        <option selected disabled>Sort by:</option>
                                        <option value="DESC">Date added(newest)</option>
                                        <option value="ASC">Date added(oldest)</option>
                                    </select>
                                </div>
                                <div class="search__button">
                                    <button type="submit">Search</button>
                                </div>
                                <div class="contact__link">
                                    <a href="mailto:yourquizwebsite@gmail.com">Contact Information</a>
                                </div>
                            </form>
                        </div>
                </section>
            </div>


        </section>


        <!-- POPUP -->


        <c:forEach items="${requestScope.registrations}" var="r">


        </c:forEach>

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>



        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/swiper.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
        <script src="${pageContext.request.contextPath}/js/register.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/unauthorizedAccess.js"></script>
        <script src="${pageContext.request.contextPath}/js/registerPopup.js"></script>


        <script src="${pageContext.request.contextPath}/js/customer/customerRegistration.js" ></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 1, "registrationsearch", "${requestScope.queryString}");</script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

    </body>

</html>