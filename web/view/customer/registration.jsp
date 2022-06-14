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
    </head>

    <body>
        <!-- HEADER -->
        <header>
            <div class="heading_logo">
                <p>LOGO</p>
            </div>
            <nav>
                <ul class="nav_links">
                    <li><a href="index.html">Home</a></li>
                    <li><a href="#">Subject</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="registration.html">My Registration</a></li>
                    <li><a href="pratice.html">Practice</a></li>
                    <li>
                        <a href="#" class="login" id="loginButton"><i class="fa fa-user-alt"></i>User Name</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="#">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>


        <!-- PAGE CONTENT -->
        <section id="main">
            <div class="registration__content">


                <aside class="left">
                    <div class="registration__list">
                        <table>
                            <tr>
                                <td>ID</td>
                                <td>Subject</td>
                                <td>Registration Time</td>
                                <td>Package</td>
                                <td>Total cost</td>
                                <td>Status</td>
                                <td>Valid from</td>
                                <td>Valid to</td>
                                <td>Action</td>
                            </tr>
                            <c:forEach items="${requestScope.registrations}" var="r">
                                <tr>
                                    <td>${r.registrationID}</td>
                                    <td>${r.course.courseName}</td>
                                    <td>${r.registrationTime}</td>
                                    <td>${r.pricePackage.priceName}</td>
                                    <td>${r.totalCost}</td>
                                    <c:choose>
                                        <c:when test="${r.status == true}"><td>Paid</td></c:when>
                                        <c:otherwise><td>Submitted</td> </c:otherwise>
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
                                        <form method="POST" action="registrationsearch">
                                            <input type="hidden" value="${requestScope.queryString}" name="queryString">
                                            <input type="hidden" value="${r.registrationID}" name="registrationID">
                                            <input type="submit" value="Cancel" onclick="return confirm('Are you sure you want to delete?')" />
                                        </form>


                                        <button onclick="openPopup(${r.registrationID})" value="${r.course.courseID}" id="${r.course.courseID}button">Edit</button>


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

                        </table>
                    </div>
                    <div id="pagination" class="pagination"></div>

                    <!--                    <div class="registration__pagination">
                                            <div class="registration__pagination-bar">
                                                <div id="pagination" class="pagination"></div>
                    
                    
                                            </div>
                                        </div>-->
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
                                    <a href="#">Contact Information</a>
                                </div>
                            </form>
                        </div>
                </section>
            </div>


        </section>


        <!-- POPUP -->
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
        <c:forEach items="${requestScope.registrations}" var="r">


        </c:forEach>


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