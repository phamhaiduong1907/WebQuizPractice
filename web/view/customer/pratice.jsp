<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practice List</title>
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../css/global.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/customer/header.css">
    <link rel="stylesheet" href="../../css/customer/practice.css">
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
        <div class="practice__wrapper">

            <div class="practice__heading">
                <h1>Practice List</h1>
            </div>

            <div class="practice__content">

                <div class="practice__tool-options">
                    <div class="practice__filter">
                        <select name="subjectName" id="">
                            <option value="">All Subjects</option>
                            <option value="">Subject 1</option>
                            <option value="">Subject 2</option>
                            <option value="">Subject 3</option>
                        </select>
                    </div>

                    <div class="practice__links">
                        <a href="#">Simulation Exam</a>
                        <a href="#">New Practice</a>
                    </div>
                </div>

                <div class="practice__list">
                    <table>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Subject Name</p>
                                <p>Exam Name</p>
                            </td>
                            <td>
                                <p>10/09/2019</p>
                                <p>Date taken</p>
                            </td>
                            <td>
                                <p>xx Corects</p>
                                <p>yy Questions</p>
                            </td>
                            <td>
                                <p>50%</p>
                                <p>Correct</p>
                            </td>
                            <td>
                                <a href="practice_detail.html">View Detail</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="test__type">
                                    <p>Test type: Put the test name here</p>
                                    <p>Duration: hh:mm:ss</p>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

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