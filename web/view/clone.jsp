<%-- 
    Document   : clone
    Created on : Jun 14, 2022, 5:53:15 PM
    Author     : Zuys
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>JSP Page</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clone.css">
    </head>
    <body>
        <div class="top__header">
            <div class="top__header__logo">
                <p>Logo</p>
            </div>

            <!--dropdown menu-->
            <div class="user_bar">
                <div class="user_log">
                    <i class="fa fa-user-circle"></i>
                    <span class="user_name">Administrator</span>
                    <div class="submenu">
                        <ul>
                            <li><a href="#" id="openProfile">User Profile</a></li>
                            <li><a href=" logout">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </div> 
        </div>

        <div class="nav__header">
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
        </div>
    </body>
</html>
