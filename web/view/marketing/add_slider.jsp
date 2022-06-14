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
                        <li><a href="list">Log out</a></li>
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
                <form action="add" method="POST"enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td><label for="title">Title:</label></td>
                            <td><input type="text" id="title" name="title" required></td>
                        </tr>
                        <tr>
                            <td><label for="backlink">Backlink:</label></td>
                            <td><input type="text" id="backlink" name="backlink" required></td>
                        </tr>
                        <tr>
                            <td><label for="status">Status:</label></td>
                            <td>
                                <select name="status" id="status">
                                    <option value="inactive">Hide</option>
                                    <option value="active">Show</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="note">Note:</label></td>
                            <td>
                                <textarea name="note" id="note" ></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="imageURL">Slider Image:</label></td>
                            <td>
                                <input type='file' id="imageURL" name="image" 
                                       onchange="readURL(this); checkValue('imageURL','previewImage');" required />
                                <div class="slider__image">
                                    <img id="previewImage" src="#" style="display: none;"/>
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
</body>

</html>