<%-- 
    Document   : new_subject
    Created on : Jun 9, 2022, 10:59:42 AM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>New subject</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/new_subject.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">
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
                        <li><a href="marketing/bloglist?search=">Posts</a></li>
                        <li><a href="slider/list">Sliders</a></li>
                        <li><a href="admin/userlist">Users</a></li>
                        <li><a href="managesubject">Course</a></li>
                    </ul>
                </nav>
            </aside>
            <aside class="right">
                <div class="right_content">
                    <div class="function_name">
                        <h1>New subject</h1>
                    </div>
                    <form action="newsubject" method="POST" id="frmSearch" autocomplete="off" enctype="multipart/form-data">
                        <div class="course_info">
                            <div class="info">
                                <div class="info_item">
                                    <label for="name">Subject name</label>
                                    <input type="text" id="name" name="name" placeholder="Enter subject name" required>
                                </div>
                                <div class="info_item">
                                    <label for="owner">Owner email</label>
                                    <input type="text" list="owners" id="owner" name="owner" placeholder="Enter owner's email" required>
                                    <datalist id="owners">
                                        <c:forEach items="${requestScope.expertList}" var="e">
                                            <option value="${e.username}">
                                        </c:forEach>
                                    </datalist>
                                </div>
                                <div class="info_item">
                                    <label for="category">Choose the category:</label>
                                    <select id="category" name="category" class="category" onchange="submitForm();">
                                        <c:forEach var="c" items="${requestScope.categories}">
                                            <option ${(c.categoryID==requestScope.cid)?"selected=\"selected\"":""}
                                                value="${c.categoryID}">${c.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="info_item">
                                    <label for="subcategory">Choose the subcategory:</label>
                                    <select id="subcategory" class="subcategory" name="subcategory">
                                        <c:forEach var="sc" items="${requestScope.subcategories}">
                                            <option value="${sc.subcategoryID}">${sc.subcategoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="thumbnail">
                                <img src="images/subject/default.png" id="photo">
                                <input type="file" name="profilePicture" id="profilePicture" onchange="return fileValidation()" oninvalid="this.setCustomValidity('Please select a picture!')" oninput="this.setCustomValidity('')">
                                <label for="profilePicture" title="Please update your picture!" id="uploadBtn">Choose Photo</label>
                            </div>
                        </div>
                        <div class="authorize_info">
                            <div class="authorize_item published">
                                <br><br>
                                <label for="published">Choose whether the subject is published or not:</label>
                                <select name="published" id="published">
                                    <option value="1">Published</option>
                                    <option value="0">Unpublished</option>
                                </select>
                                <br>
                                <label for="featured">Choose whether the subject is featured or not:</label>
                                <select name="featured" id="featured">
                                    <option value="1">Featured</option>
                                    <option value="0">Unfeatured</option>
                                </select>
                            </div>
                            <div class="authorize_item description">
                                <br><br>
                                <label for="description">Description</label>
                                <textarea required name="description" id="description" rows="10" cols="90" placeholder="Enter the course description.."></textarea>
                            </div>
                        </div>
                        <br>
                        <div class="save_button_new_subject">
                            <button type="submit">Save</button>
                        </div>
                        <br>
                        <div class="message__box">
                            <p>${requestScope.create_subject_status}</p>
                        </div>

                    </form>
                </div>
                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="images/close.png" alt="" class="close">

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
                            <input type="radio" name="" id="">
                            <p>Male</p>
                            <input type="radio" name="" id="">
                            <p>Female</p>
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
        <script src="js/new_subject.js"></script>
        <script src="js/userPopup.js"></script>
        <script src="js/profile.js"></script>
    </body>

</html>
