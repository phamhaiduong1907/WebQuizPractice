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
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="#">Sliders</a></li>
                        <li><a href="#">Course</a></li>
                        <li><a href="#">Test</a></li>
                        <li><a href="#">Quiz</a></li>
                        <li><a href="#">System Settings</a></li>
                        <li><a href="#">Users</a></li>
                    </ul>
                </nav>
            </aside>
            <aside class="right">
                <div class="right_content">
                    <h1>User Details</h1>
                    <form action="#">
                        <div class="personal_info">
                            <div class="info">
                                <div class="info_item">
                                    <label for="name">First Name</label>
                                    <input type="text" id="firstName" name="firstName" placeholder="Enter your first name"
                                           value="${requestScope.user.firstName}">
                                </div>
                                <div class="info_item">
                                    <label for="name">Last Name</label>
                                    <input type="text" id="lastName" name="lastName" placeholder="Enter your last name"
                                           value="${requestScope.user.lastName}">
                                </div>
                                <div class="info_item">
                                    <label for="email">Email</label>
                                    <input type="text" id="email" name="email" placeholder="abc@gmail.com"
                                           value="${requestScope.user.account.username}">
                                </div>
                                <div class="info_item">
                                    <label for="mobile">Mobile</label>
                                    <input type="text" id="mobile" name="phone" placeholder="Mobile"
                                           value="${requestScope.user.phoneNumber}">
                                </div>
                                <div class="info_item">
                                    <label for="gender">Gender</label>
                                    <input type="radio" name="gender" value="male" ${requestScope.user.gender ? "checked" : ""}>Male
                                    <input type="radio" name="gender" value="female" ${requestScope.user.gender ? "" : "checked"}>Female
                                </div>
                            </div>
                            <div class="avatar">
                                <p>AVATAR HERE</p>
                                <div class="add_photo">
                                    <p>Add Photo</p>
                                </div>
                            </div>
                        </div>
                        <div class="authorize_info">
                            <div class="authorize_item">
                                <label for="role">Role</label>
                                <select name="roleID" id="role">
                                    <c:forEach items="${requestScope.roles}" var="role">
                                        <option value="${role.roleID}" ${role.roleID == requestScope.user.account.role.roleID ?"selected":""}>
                                            ${role.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <div class="authorize_item">
                                <label for="status">Status</label>
                                <select name="status" id="status">
                                    <option value="true">active</option>
                                    <option value="false">inactive</option>
                                </select>
                            </div> 
                            <div class="authorize_item authorize_address">
                                <label for="address">Address</label>
                                <input type="text" id="address" placeholder="Enter your adress"
                                       value="${requestScope.user.address}">
                            </div> 
                        </div>
                        <button type="submit">submit</button>
                    </form>
                </div>
                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="../../images/close.png" alt="" class="close">

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
                            <input type="radio" name="" id=""> <p>Male</p>
                            <input type="radio" name="" id=""> <p>Female</p>
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

        <script src="../../js/userPopup.js"></script>
    </body>
</html>