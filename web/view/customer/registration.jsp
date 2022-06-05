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
    <link rel="stylesheet" href="../../css/global.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/customer/header.css">
    <link rel="stylesheet" href="../../css/customer/registration.css">
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
                        <tr>
                            <td>A01</td>
                            <td>Web Development</td>
                            <td>1/1/2022</td>
                            <td>10 months</td>
                            <td>200$</td>
                            <td>Active</td>
                            <td>1/1/2022</td>
                            <td>1/11/2022</td>
                            <td>-</td>
                        </tr>
                        <tr>
                            <td>A01</td>
                            <td>Web Development</td>
                            <td>1/1/2022</td>
                            <td>10 months</td>
                            <td>200$</td>
                            <td>Submitted</td>
                            <td></td>
                            <td></td>
                            <td>
                                <a href="#">Cancel</a>
                                <a href="#">Edit</a>
                            </td>
                        </tr>
                        <tr>
                            <td>A01</td>
                            <td>Web Development</td>
                            <td>1/1/2022</td>
                            <td>10 months</td>
                            <td>200$</td>
                            <td>Active</td>
                            <td>1/1/2022</td>
                            <td>1/11/2022</td>
                            <td>
                                <a href="#">Cancel</a>
                                <a href="#">Edit</a>
                            </td>
                        </tr>
                        <tr>
                            <td>A01</td>
                            <td>Web Development</td>
                            <td>1/1/2022</td>
                            <td>10 months</td>
                            <td>200$</td>
                            <td>Active</td>
                            <td>1/1/2022</td>
                            <td>1/11/2022</td>
                            <td>
                                <a href="#">Cancel</a>
                                <a href="#">Edit</a>
                            </td>
                        </tr>
                        <tr>
                            <td>A01</td>
                            <td>Web Development</td>
                            <td>1/1/2022</td>
                            <td>10 months</td>
                            <td>200$</td>
                            <td>Active</td>
                            <td>1/1/2022</td>
                            <td>1/11/2022</td>
                            <td>
                                <a href="#">Cancel</a>
                                <a href="#">Edit</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="registration__pagination">
                    <div class="registration__pagination-bar">
                        <ul>
                            <li>
                                <a href="#">
                                    << </a>
                            </li>
                            <li>
                                <a href="#">2</a>
                            </li>
                            <li>
                                <a href="#">3</a>
                            </li>
                            <li>
                                <a href="#">4</a>
                            </li>
                            <li>
                                <a href="#">>></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </aside>


            <aside class="right">
                <div class="registration__search-wrapper">

                    <div class="registration__search-box">
                        <div class="registration__search-bar">
                            <form action="#">
                                <input type="text" name="subjectName" id="subjectName" placeholder="Enter subject name to search">
                            </form>
                        </div>
                    </div>

                    <div class="registration__search-category">
                        <h4>Category</h4>
                        <div class="registration__search-options">
                            <form action="#">
                                <div class="registration__search-option-item">
                                    <input type="checkbox" name="categoryName">
                                    <span>Category 1</span>
                                </div>
                                <div class="registration__search-option-item">
                                    <input type="checkbox" name="categoryName">
                                    <span>Category 2</span>
                                </div>
                                <div class="registration__search-option-item">
                                    <input type="checkbox" name="categoryName">
                                    <span>Category 3</span>
                                </div>
                                <div class="registration__search-option-item">
                                    <input type="checkbox" name="categoryName">
                                    <span>Category 4</span>
                                </div>

                                <div class="registration__form-search-btn">
                                    <button type="submit">Search</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="contact__links">
                        <a href="#">Contact</a>
                    </div>
                </div>
            </aside>
        </div>


    </section>


    <!-- POPUP -->
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


    <script src="../../js/userPopup.js"></script>
</body>

</html>