<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Adding</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../css/admin/index.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/testcontent/detail.css">

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
                    <li><a href="../admin/system.html">System Settings</a></li>
                    <li><a href="../admin/user_list.html">Users</a></li>
                    <li><a href="#">Course</a></li>
                    <li><a href="quizlist.html">Test</a></li>
                    <li><a href="#">Quiz</a></li>
                </ul>
            </nav>
        </aside>
        <aside class="right">
            <div class="right_content">
                <div class="right_header">
                    <h1><i class="fa-solid fa-question" aria-hidden="true"></i></i>Adding quiz</h1>
                </div>
                <div class="quiz__detail">
                    <div class="quiz__left">
                        <div class="quiz__name">
                            <label>Quiz name: </label>
                            <input type="text">
                        </div>
                        <div class="subject__name">
                            <label>Subject: </label>
                            <input type="text">
                        </div>
                        <div class="quiz__type">
                            <label>Quiz type: </label>
                            <select>
                                <option value="" disabled selected>Quiz Type</option>
                                <option value="">Image</option>
                                <option value="">Theory</option>
                            </select>
                        </div>
                        <div class="quiz__attachment">
                            <button>
                                <input type="file" name="" id="file" style="display: none;">
                                <label for="file">Add quiz attachment</label>
                            </button>
                            <i class="fa-solid fa-upload"></i>
                        </div>
                        <div class="quiz__save">
                            <button>Save</button>
                        </div>
                    </div>
                    <div class="quiz__right">
                        <div class="quiz__level">
                            <label for="">Level: </label>
                            <select name="" id="">
                                <option value="" disabled selected>Level</option>
                                <option value="">Easy</option>
                                <option value="">Medium</option>
                                <option value="">Hard</option>
                            </select>
                        </div>
                        <div class="quiz__duration">
                            <label for="">Duration: </label>
                            <input type="text">
                            <label for="">hours</label>
                            <input type="text">
                            <label for="">minutes</label>
                        </div>
                        <div class="quiz__download">
                            <a href="" download="">Download quiz template
                            </a>
                            <i class="fa-solid fa-download"></i>
                        </div>
                    </div>
                </div>
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

    <script src="../../js/userPopup.js"></script>
</body>

</html>