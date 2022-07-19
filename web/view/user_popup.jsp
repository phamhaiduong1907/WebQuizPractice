<%-- 
    Document   : user_popup
    Created on : Jun 16, 2022, 11:58:39 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">

<c:if test="${sessionScope.account != null}">
    <section class="popup <c:choose>
                 <c:when test="${sessionScope.profile_status != null || sessionScope.changepass_status != null}">
                     <%="active"%>
                 </c:when>
             </c:choose>" id="popupSection">
        <div class="popup__content">
            <div class="form__user-profile" style="display: <c:choose>
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
                        <label for="profilePicture" title="Please update your picture!" id="uploadBtn">Choose Photo</label>
                    </div>
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" value="<c:out value="${sessionScope.user.account.username}"/>" disabled placeholder="Your email">
                    <label for="firstName">First Name</label>
                    <input type="text" name="firstName" id="firstName" value="<c:out value="${sessionScope.user.firstName}"/>" title="Your name can't be empty" placeholder="Enter your first name" required>
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastName" id="lastName" value="<c:out value="${sessionScope.user.lastName}"/>" title="Your name can't be empty" placeholder="Enter your last name" required>

                    <div class="profile__gender signup__gender">
                        <h5>Gender</h5>
                        <div class="gender__select">
                            <div class="gender__item">
                                <input type="radio" name="gender" value="male" <c:if test="${sessionScope.user.gender}">
                                       checked="checked"
                                    </c:if> id="" required>Male
                            </div>
                            <div class="gender__item">
                                <input type="radio" name="gender" value="female" <c:if test="${!sessionScope.user.gender}">
                                       checked="checked"
                                    </c:if> id="" required>Female
                            </div>
                        </div>
                    </div>
                    <label for="phone">Phone Number</label>
                    <input type="text" name="phone" id="phone" value="<c:out value="${sessionScope.user.phoneNumber}"/>" pattern="[0-9]{9,10}" title="Please enter a valid phone number (9-10 number with no separator)" placeholder="Enter your phone" required>
                    <label for="address">Address</label>
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

            <div class="form__change-password">
                <h2>Change Password</h2>
                <form action="changepass" method="POST" onsubmit="return checkOldNewPass()">
                    Enter current password: <input type="password" name="currentPassword" id="currentPassword" required title="Must matches current password">
                    Enter new password: <input type="password" name="newPassword" id="newPassword" onchange="checkPassword()" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Must be at minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character">
                    Confirm new password: <input type="password" name="confirmNewPassword" id="confirmNewPassword" required>
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
    <section class="popup <c:choose>
                 <c:when test="${sessionScope.register_status != null || param.resetPasswordMessage != null 
                                 || sessionScope.login_status != null}">
                     <%="active"%>
                 </c:when>
             </c:choose>" id="popupSection">


        <div class="popup__content">
            <div class="popup__login-form" id="popupLoginForm" style="display: <c:choose>
                     <c:when test="${sessionScope.register_status == null && param.resetPasswordMessage == null}">
                         <%="block; "%>
                     </c:when>
                     <c:otherwise>
                         <%="none;"%>
                     </c:otherwise>
                 </c:choose>">

                <span class="welcome__status status__login">already a member</span>
                <h2>Login</h2>
                <div class="form__login" >

                    <form action="${pageContext.request.contextPath}/login" method="POST">
                        <label for="emailLogin">Username</label>
                        <input type="text" name="email" id="emailLogin" required>
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password"required>
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
                     <c:when test="${sessionScope.register_status != null }">
                         <%="block; "%>
                     </c:when>
                     <c:otherwise>
                         <%="none;"%>
                     </c:otherwise>
                 </c:choose>">
                <i class="fa fa-arrow-left"></i>
                <span class="welcome__status status__signup">I do not have an account</span>
                <h2>Sign up</h2>
                <div class="form__signup">
                    <form action="register" method="POST">
                        <label for="firstName">First Name</label>
                        <input type="text" name="firstName" id="firstName" required autocomplete="off">
                        <label for="lastName">Last Name</label>
                        <input type="text" name="lastName" id="lastName" required autocomplete="off">
                        <div class="signup__gender">
                            <h5>Gender</h5>
                            <div class="gender__select">
                                <div class="gender__item">
                                    <input type="radio" name="gender" value="male"required>Male
                                </div>
                                <div class="gender__item">
                                    <input type="radio" name="gender" value="female"required>Female
                                </div>
                            </div>
                        </div>
                        <label for="emailSignup">Email</label>
                        <input type="text" autocomplete="off" name="email" id="emailSignup" pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" title="Must be in email format (eg: abc@xyz.com)" required>
                        <label for="phone">Phone Number</label>
                        <input type="text" autocomplete="off" name="phone" id="phone" pattern="[0-9]{9,10}" title="Must be between 9 and 10 digit" required>
                        <label for="address">Address</label>
                        <input type="text" autocomplete="off" name="address" id="address" title="Must not be empty" required>
                        <label for="passwordReg">Password</label>
                        <input type="password" autocomplete="off" name="passwordReg" id="passwordReg" onchange="matchPassword()" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Must be at minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character" required>
                        <label for="ConfirmPasswordReg">Confirm Password</label>
                        <input type="password" autocomplete="off" name="confirmPasswordReg" id="confirmPassword" required>
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


<c:if test="${sessionScope.account == null}">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</c:if>
<c:if test="${sessionScope.account != null}">
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
</c:if>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>
<script src="${pageContext.request.contextPath}/js/changepass.js"></script>
<script src="${pageContext.request.contextPath}/js/common/home.js"></script>