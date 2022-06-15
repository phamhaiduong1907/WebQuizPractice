<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider Details</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slider/slider_detail.css">
    </head>

    <body>
        <header>
            <div class="logo">
                <p>LOGO</p>
            </div>

            <div class="user_bar">
                <div class="user_log">
                    <i class="fa fa-user-circle"></i>
                    <span class="user_name">Marketing</span>
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
            <!-- LEFT NAVIGATION BAR -->
            <aside class="left">
                <nav>
                    <ul>
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="list">Sliders</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="right_content">
                    <form action="detail" method="POST" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><label for="sliderID">ID:</label></td>
                                <td>
                                    <input type="text" id="sliderID" name="sliderID" value="${requestScope.sliderByID.sliderID}" readonly>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="title">Title:</label></td>
                                <td>
                                    <input type="text" id="title" name="title" value="${requestScope.sliderByID.title}">
                                </td>
                            </tr>
                            <tr>
                                <td><label for="backlink">Backlink:</label></td>
                                <td>
                                    <input type="text" id="backlink" name="backlink" value="${requestScope.sliderByID.backlink}">
                                </td>
                            </tr>
                            <tr>
                                <td><label for="status">Status:</label></td>
                                <td>
                                    <select name="status" id="status">
                                        <option value="inactive" ${requestScope.sliderByID.status?"":"selected"}>Hide</option>
                                        <option value="active" ${requestScope.sliderByID.status?"selected":""}>Show</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="note">Note:</label></td>
                                <td>
                                    <textarea name="note" id="note">${requestScope.sliderByID.note}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="imageURL">Slider Image:</label></td>
                                <td>
                                    <input type="hidden" name="imageURLDefault" value="${requestScope.sliderByID.imageUrl}">
                                    <input type='file' id="imageURL" name="imageURLUpdate" onchange="readURL(this); checkValueUpdate('imageURL', 'previewImage','${pageContext.request.contextPath}/${requestScope.sliderByID.imageUrl}');"/>
                                    <div class="slider__image">
                                        <img id="previewImage" src="${pageContext.request.contextPath}/${requestScope.sliderByID.imageUrl}"
                                             style="width: 90%; height: 200px;">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <div class="slider__submit">
                            <input type="submit" value="Save">
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

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/slider/uploadFile.js"></script>
        <script>
                                        function checkValueUpdate(id, targetId, src) {
                                            var input = document.getElementById(id).value;
                                            var image = document.getElementById(targetId);
                                            if (input === "" || input === undefined) {
                                                image.src = src;
                                                image.style.width = '90%';
                                                image.style.height = '200px';
                                            }
                                        }
        </script>
    </body>

</html>