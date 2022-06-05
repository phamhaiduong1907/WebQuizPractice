<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practice Detail</title>

    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="../../css/global.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/customer/header.css">
    <link rel="stylesheet" href="../../css/customer/practice_detail.css">
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
                    <a href="#" class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                        User Name
                    </a>
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
        <div class="detail__wrapper">
            
            <div class="detail__heading">
                <h1>Practice Detail</h1>
            </div>
            
            <form action="#">
                <div class="detail__options">
                    <div class="detail__options-item">
                        <label for="subjectName">Subject</label>
                        <select name="subjectName" id="subjectName">
                            <option value="">Subject Name</option>
                            <option value="">Subject Name</option>
                            <option value="">Subject Name</option>
                        </select>
                    </div>
                    <div class="detail__options-item">
                        <label for="questionNum">Number of practicing question</label>
                        <input type="text" name="questionNum" id="questionNum" placeholder="Enter the number of questions">
                    </div>
                    <div class="detail__options-item">
                        <label for="questionType">Question are selected by topics or a specific dimension?</label>
                        <select name="questionType" id="questionType">
                            <option value="">By subject topic</option>
                            <option value="">By dimension</option>
                        </select>
                    </div>
                    <div class="detail__options-item">
                        <label for="questionGroup">Question groups(Choose one or all)?</label>
                        <select name="questionGroup" id="questionGroup">
                            <option value="">All</option>
                            <option value="">One</option>
                        </select>
                    </div>
                </div>

                <div class="detail__form-submit-btn">
                    <button type="submit">Practice</button>
                </div>
            </form>
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