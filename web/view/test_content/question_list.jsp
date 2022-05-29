<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="../../css/admin/index.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/admin/system.css">
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
                    <li><a href="#">Test</a></li>
                    <li><a href="#">Quiz</a></li>
                </ul>
            </nav>
        </aside>

        <aside class="right">
            <div class="right_content">
                <h1>QUESTION LIST</h1>
                <div class="setting_tool">
                    <div class="search_form">
                        <form action="#" id="search">
                            <select name="" id="#">
                                <option value="">All subject</option>
                                <option value="">Subject 1</option>
                                <option value="">Subject 2</option>
                                <option value="">...</option>
                            </select>
                            <select name="" id="#">
                                <option value="">All lesson</option>
                                <option value="">Lesson 1</option>
                                <option value="">Lesson 2</option>
                                <option value="">...</option>
                            </select>
                            <select name="" id="#">
                                <option value="">All dimension</option>
                                <option value="">Dimension 1</option>
                                <option value="">Dimension 2</option>
                                <option value="">...</option>
                            </select>
                            <select name="" id="#">
                                <option value="">All level</option>
                                <option value="">Easy</option>
                                <option value="">Medium</option>
                                <option value="">Hard</option>
                            </select>
                            <select name="" id="#">
                                <option value="">All status</option>
                                <option value="">Active</option>
                                <option value="">Inactive</option>
                            </select>
                            <input type="text" name="" id="" placeholder="Type name to search">
                            <button type="submit">Search</button>
                        </form>
                    </div>
                    <div class="add_setting">
                        <a href="#">Import Question</a>
                    </div>
                </div>
                <table class="setting_list">
                    <tr>
                        <td>ID</td>
                        <td>Content</td>
                        <td>Subject</td>
                        <td>Dimension</td>
                        <td>Lesson</td>
                        <td>Level</td>
                        <td>Status</td>
                        <td>Action</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Content of lesson 1</td>
                        <td>Subject 1</td>
                        <td>Dimension 1</td>
                        <td>Lesson 1</td>
                        <td>Easy</td>
                        <td>Active</td>
                        <td>
                            <a href="#">Edit</a>
                            <a href="#">Deactive</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Content of lesson 2</td>
                        <td>Subject 2</td>
                        <td>Dimension 2</td>
                        <td>Lesson 2</td>
                        <td>Medium</td>
                        <td>Inactive</td>
                        <td>
                            <a href="#">Edit</a>
                            <a href="#">Activate</a>
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