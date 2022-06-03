<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../css/admin/index.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/testcontent/list.css">

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
                    <h1><i class="fa-solid fa-question" aria-hidden="true"></i></i>Quiz List</h1>
                </div>
                <div class="quiz__list__tool">
                    <div class="search_form">
                        <form action="#" class="search__section">
                            <select name="" id="#">
                                <option value="" disabled selected>Subjects</option>
                                <option value="">Subject 1</option>
                                <option value="">Subject 2</option>
                                <option value="">Subject 3</option>
                            </select>
                            <select name="" id="#">
                                <option value="" disabled selected>Quiz Type</option>
                                <option value="">Active</option>
                                <option value="">Inactive</option>
                            </select>
                            <input type="text" name="" id="" placeholder="Search by name">
                            <button type="submit">Search</button>
                        </form>
                    </div>
                    <div class="adding__quiz">
                        <a href="addingquiz.html">Add a quiz</a>
                    </div>
                </div>
                <table class="quiz__list">
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Subject</td>
                        <td>Level</td>
                        <td>Question</td>
                        <td>Duration</td>
                        <td>Pass rate</td>
                        <td>Quiz type</td>
                        <td>Function</td>
                    </tr>
                    <tr>
                        <td>142</td>
                        <td>Quiz 1</td>
                        <td>Math</td>
                        <td>Easy</td>
                        <td>U/K</td>
                        <td>1 hour</td>
                        <td>90%</td>
                        <td>Image</td>
                        <td>
                            <a href="quizdetail.html">Edit quiz detail</a>
                        </td>
                    </tr>
                    <tr>
                        <td>143</td>
                        <td>Quiz 2</td>
                        <td>Math</td>
                        <td>Easy</td>
                        <td>U/K</td>
                        <td>1 hour</td>
                        <td>90%</td>
                        <td>Image</td>
                        <td>
                            <a href="quizdetail.html">Edit quiz detail</a>
                        </td>
                    </tr>
                    <tr>
                        <td>144</td>
                        <td>Quiz 3</td>
                        <td>Math</td>
                        <td>Medium</td>
                        <td>U/K</td>
                        <td>1 hour</td>
                        <td>80%</td>
                        <td>Image</td>
                        <td>
                            <a href="quizdetail.html">Edit quiz detail</a>
                        </td>
                    </tr>
                    <tr>
                        <td>145</td>
                        <td>Quiz 4</td>
                        <td>Math</td>
                        <td>Medium</td>
                        <td>U/K</td>
                        <td>1 hour</td>
                        <td>80%</td>
                        <td>Image</td>
                        <td>
                            <a href="quizdetail.html">Edit quiz detail</a>
                        </td>
                    </tr>
                    <tr>
                        <td>146</td>
                        <td>Quiz 5</td>
                        <td>Math</td>
                        <td>Hard</td>
                        <td>U/K</td>
                        <td>1 hour</td>
                        <td>75%</td>
                        <td>Image</td>
                        <td>
                            <a href="quizdetail.html">Edit quiz detail</a>
                        </td>
                    </tr>
                </table>
                <div class="pagination">
                    <ul>
                        <li>
                            << </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li> >> </li>
                    </ul>
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