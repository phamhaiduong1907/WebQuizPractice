<%-- 
    Document   : post_list
    Created on : Jun 12, 2022, 1:18:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Post list</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/default_marketing.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/add_post.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">



    </head>

    <body>
        <!-- HEADER -->
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <section class="main">
            <!-- RIGHT CONTENT -->
            <a href="addblog" class="addlink">Add blog</a>

            <aside class="right">
                <!--Starting of the filter-->

                <c:choose>
                    <c:when test="${requestScope.posts.size() eq 0}">
                        <div class="no__result">
                            There are no records matching the search query
                        </div>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
                <div class="table__data">
                    <table>
                        <thead>
                            <tr>
                                <th>Post ID</th>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Feature</th>
                                <th>Status</th>
                                <th>Author</th>
                                <th>Updated date</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.posts}" var="p">
                                <tr>
                                    <td>${p.postID}</td>
                                    <td>${p.title}</td>
                                    <td>${p.subcategory.subcategoryName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.isFeatured}">
                                                Yes
                                            </c:when>
                                            <c:otherwise>
                                                No
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.status}">
                                                On
                                            </c:when>
                                            <c:otherwise>
                                                Off
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${p.author.username}</td>
                                    <td>${p.updatedDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.status == true}">
                                                <a href="changeblogstatus?postID=${p.postID}&status=false" class="action">Hide</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="changeblogstatus?postID=${p.postID}&status=true" class="action">Show</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="view?postID=${p.postID}" class="view">View</a></td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="pagination" class="pagination"></div>
                </div>



                <section class="option__box">
                    <div class="option__filter">
                        <div class="option__searchbar">
                            <form action="bloglist" method="GET">
                                <input type="text" name="search"  placeholder="Type something to search...">
                                </div>
                                <div class="option__checkbox">
                                    <h3>Category</h3>
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
            </aside>

            <c:if test="${sessionScope.account != null}">
                <section class="popup" style="display: <c:choose>
                             <c:when test="${sessionScope.profile_status != null || sessionScope.changepass_status != null}">
                                 <%="flex; "%>
                             </c:when>
                             <c:otherwise>
                                 <%="none;"%>
                             </c:otherwise>
                         </c:choose>">
                    <div class="popup__content">
                        <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

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
                                            <img src="${pageContext.request.contextPath}/${sessionScope.user.profilePictureUrl}" id="photo">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/images/profile/default.jpg" id="photo">
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
                                Enter current password: <input type="password" name="currentPassword" id="currentPassword" required pattern="${sessionScope.account.password}" title="Must matches current password" placeholder="Enter your current password">
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
            </c:if>
            <c:if  test="${sessionScope.account == null}">
                <!-- POPUP -->
                <section class="popup" id="popupSection" style="display: <c:choose>

                         <c:when test="${sessionScope.login_status != null || sessionScope.register_status != null || param.resetPasswordMessage != null }">
                             <%="flex; "%>
                         </c:when>
                         <c:otherwise>
                             <%="none;"%>
                         </c:otherwise>
                    </c:choose>">


                    <div class="popup__content">
                        <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

                        <div class="popup__login-form" id="popupLoginForm" style="display: <c:choose>
                                 <c:when test="${sessionScope.login_status != null }">
                                     <c:out value="block;"/>
                                 </c:when>
                                 <c:otherwise>
                                     <c:out value="none;"/>
                                 </c:otherwise>
                             </c:choose>">

                            <h2>Welcome to Quiz Practice</h2>
                            <div class="form__login" >

                                <form action="login" method="POST">
                                    <input type="text" name="email" id="emailLogin" placeholder="Enter your email" required>
                                    <input type="password" name="password" id="password" placeholder="Enter your password" required>
                                    <div class="popup__reset">
                                        <a href="#">Forgot password?</a>
                                    </div>
                                    <div class="form__button">
                                        <button type="submit">Login</button>
                                    </div>
                                </form>

                            </div>
                            <div class="message__box">
                                <p>${sessionScope.login_status}</p>   
                                <c:remove var="login_status" scope="session"/>
                            </div>


                            <div class="popup__signup" >
                                <a href="#">Don't have any account? Sign up here</a>
                            </div>
                        </div>



                        <div class="popup__signup-form" style="display: <c:choose>
                                 <c:when test="${sessionScope.register_status != null}">
                                     <%="block; "%>
                                 </c:when>
                                 <c:otherwise>
                                     <%="none;"%>
                                 </c:otherwise>
                             </c:choose>">
                            <i class="fa fa-arrow-left"></i>
                            <h2>Register for Quiz Practice</h2>
                            <div class="form__signup">
                                <form action="register" method="POST">
                                    <input type="text" name="firstName" id="firstName" placeholder="First Name" required>
                                    <input type="text" name="lastName" id="lastName" placeholder="Last Name" required>
                                    <div class="signup__gender">
                                        <h5>Gender</h5>
                                        <input type="radio" name="gender" value="male" required>Male
                                        <input type="radio" name="gender" value="female" required>Female
                                    </div>
                                    <input type="text" name="email" id="emailSignup" pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" title="Must be in email format (eg: abc@xyz.com)" placeholder="Email" required>
                                    <input type="text" name="phone" id="phone" pattern="[0-9]{9,10}" title="Must be between 9 and 10 digit" placeholder="Phone Number" required>
                                    <input type="text" name="address" id="address" title="Must not be empty" placeholder="Address" required>
                                    <input type="password" name="passwordReg" id="passwordReg" onchange="matchPassword()" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Must be at minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"  placeholder="Password" required>
                                    <input type="password" name="confirmPasswordReg" id="confirmPassword" placeholder="Confirm password" required>
                                    <div class="form__button">
                                        <button type="submit" >Register</button>
                                    </div>
                                </form>
                            </div>
                            <div class="message__box">
                                <p>${sessionScope.register_status}</p>
                                <c:remove var="register_status" scope="session"/>
                            </div>
                        </div>

                        <div class="popup__reset-form" style="display: <c:choose>
                                 <c:when test="${param.resetPasswordMessage != null}">
                                     <%="block;"%>
                                 </c:when>
                                 <c:otherwise>
                                     <%="none;"%>
                                 </c:otherwise>
                             </c:choose>">
                            <i class="fa fa-arrow-left"></i>
                            <h2>Reset Password</h2>
                            <div class="form__reset">
                                <form action="forgotPassword" method="POST">
                                    <input type="text" name="email" id="emailReset"
                                           placeholder="Enter your email to reset your password" onkeyup='check();'>
                                    <div class="form__button">
                                        <button type="submit" id="resetButton">Verify your email</button>
                                    </div>
                                </form>

                            </div>
                            <div class="message__box">
                                <p id="validFormMessage"></p>
                                <p>${param.resetPasswordMessage}</p>  
                            </div>
                        </div>

                    </div>
                </section>
            </c:if>
            <footer>
                <p>Copyright</p>
            </footer>
        </section>


        <script src="${pageContext.request.contextPath}/js/customer/customerRegistration.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>

    </body>
</html>
