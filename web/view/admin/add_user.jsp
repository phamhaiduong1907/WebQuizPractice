<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Feature"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/user_detail.css">
    </head>
    <body>
        <header>
            <div class="logo">
                <p>LOGO</p>
            </div>

            <div class="user_bar">
                <div class="user_log">
                    <i class="fa fa-user-circle"></i>
                    <span class="user_name">Administrator</span>
                    <div class="submenu">
                        <ul>
                            <li><a href="#" id="openProfile">User Profile</a></li>
                            <li><a href="#" id="openChangePassword">Change Password</a></li>
                            <li><a href="#">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <section class="main">
            <aside class="left">
                <nav>
                    <ul>
                        <c:forEach items="${sessionScope.account.role.features}" var="f">
                            <c:if test="${f.isDisplayed == true}">
                                <li><a href="${f.url}">${f.featureName}</a></li>
                                </c:if>                            
                            </c:forEach>
                    </ul>
                </nav>
            </aside>
            <aside class="right">
                <div class="right_content">
                    <h1>Add User</h1>
                    <form action="add" method="POST">
                        <div class="personal_info">
                            <div class="info">
                                <div class="info_item">
                                    <label for="name">First Name</label>
                                    <input type="text" id="firstNameInput" name="firstName" placeholder="Enter your first name" required>
                                </div>
                                <div class="info_item">
                                    <label for="name">Last Name</label>
                                    <input type="text" id="lastNameInput" name="lastName" placeholder="Enter your last name" required>
                                </div>
                                <div class="info_item">
                                    <label for="email">Email</label>
                                    <input type="text" id="emailInput" name="email" placeholder="abc@gmail.com" required>
                                </div>
                                <div class="info_item">
                                    <label for="mobile">Mobile</label>
                                    <input type="text" id="mobileInput" name="phone" placeholder="Mobile" required>
                                </div>
                                <div class="info_item">
                                    <label for="gender">Gender</label>
                                    <input type="radio" name="gender" value="male" checked>Male
                                    <input type="radio" name="gender" value="female">Female
                                </div>
                            </div>
                            <div class="avatar">
                                <img src="${pageContext.request.contextPath}/images/default_user_avatar.png" alt="alt"/>
                            </div>
                        </div>
                        <div class="authorize_info">
                            <div class="authorize_item">
                                <label for="role">Role</label>
                                <select name="roleID" id="roleSelect">
                                    <c:forEach items="${requestScope.roles}" var="role">
                                        <option value="${role.roleID}" 
                                                ${role.roleID == requestScope.user.account.role.roleID ?"selected":""}
                                                >
                                            ${role.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <div class="authorize_item">
                                <label for="status">Status</label>
                                <select name="status" id="statusInput">
                                    <option value="active">active</option>
                                    <option value="inactive">inactive</option>
                                </select>
                            </div> 
                            <div class="authorize_item authorize_address">
                                <label for="address">Address</label>
                                <input type="text" name="address" id="addressInput" placeholder="Enter your adress" required>
                            </div> 
                        </div>
                        <button type="submit">Save</button>
                    </form>
                </div>
                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

                <div class="form_user-profile">
                    <h2>User Profile</h2>
                    <form action="#">
                        <div class="user__avatar">
                            <!-- <input type="file" name="" id=""> -->
                        </div>
                        <input type="text" name="email" id="email" disabled placeholder="Your email">
                        <input type="text" name="firstName" id="firstName" placeholder="Enter your first name">
                        <input type="text" name="lastName" id="lastName" placeholder="Enter your last name">
                        <input type="text" name="phone" id="phone" placeholder="Enter your phone">
                        <div class="profile__gender signup__gender">
                            <h5>Gender</h5>
                            <input type="radio" name=""> <p>Male</p>
                            <input type="radio" name=""> <p>Female</p>
                        </div>
                        <input type="text" name="address" id="address" placeholder="Enter your address">
                        <div class="form__button">
                            <button type="submit">Save</button>
                        </div>
                    </form>
                </div>

                <div class="form__change-password" style="display: none;">
                    <h2>Change Password</h2>
                    <form action="#">
                        <input type="password" placeholder="Enter your current password">
                        <input type="password" placeholder="Enter new password">
                        <input type="password" placeholder="Reenter your new password">
                        <div class="form__button">
                            <button type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>

        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>                    
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script>
//                        function checkSubmit() {
//                            var emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/;
//                            var phoneRegex = /[0-9]{9,10}/;
//                            var email = document.getElementById('emailInput').value.trim();
//                            var phone = document.getElementById('mobileInput').value.trim();
//                            if (!emailRegex.test(email)) {
//                                alert('Please check your email again!');
//                                return false;
//                            } else if (!phoneRegex.test(phone)) {
//                                alert('Please check your phone again!');
//                                return false;
//                            } else {
//                                return true;
//                            }
//                        }
        </script>
    </body>
</html>
