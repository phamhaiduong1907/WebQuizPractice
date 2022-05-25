<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Index</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../css/admin/index.css">
    <link rel="stylesheet" href="../../css/popup.css">
    <link rel="stylesheet" href="../../css/admin/setting_detail.css">

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
                    <li><a href="system.html">System Settings</a></li>
                    <li><a href="user_list.html">Users</a></li>
                    <li><a href="#">Course</a></li>
                    <li><a href="#">Test</a></li>
                    <li><a href="#">Quiz</a></li>
                </ul>
            </nav>
        </aside>

        <aside class="right">
            <div class="right_content">
                <h1>Setting Details</h1>
                <div class="detail_form">
                    <form action="#" id="formEdit">
                        <div class="form_content">
                            <div class="form_item">
                                <label for="lessonType">Type*</label>
                                <select name="" id="lessonType">
                                    <option value="">Lesson Type</option>
                                    <option value="">Option 1</option>
                                    <option value="">Option 2</option>
                                </select>
                            </div>
                            <div class="form_item">
                                <label for="order">Order*</label>
                                <input type="text" name="" id="order">
                            </div>
                            <div class="form_item">
                                <label for="value">Value</label>
                                <input type="text" name="" id="value">
                            </div>
                            <div class="form_item" id="statusItem">
                                <label for="status">Status</label>
                                <input type="radio" name="">Active
                                <input type="radio" name="">Inactive
                            </div>
                            <div class="form_item" id="descriptionItem">
                                <label for="description">Description</label>
                                <textarea name="" id="description"></textarea>
                            </div>
                            
                        </div>
                        <button type="submit">Submit</button>
                    </form>
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