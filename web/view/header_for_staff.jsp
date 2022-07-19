<%-- 
    Document   : header
    Created on : Jun 16, 2022, 10:49:15 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="shortcut icon" href="https://drive.google.com/file/d/1QaLMGkP3YCk6oATgxEZbaZrhkf7w87gv/view?usp=sharing">-->
<!DOCTYPE html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">

<!--Header for guest -->
<c:if test="${sessionScope.account == null}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="${pageContext.request.contextPath}/home"class="nav__item">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/subjectList"class="nav__item">Subject</a></li>
                    <li><a href="${pageContext.request.contextPath}/bloglist"class="nav__item">Blog</a></li>
                        <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="nav__item" id="loginButton">Login</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>


<!--Cusomter header when logged in-->
<c:if test="${sessionScope.account.role.roleID == 5}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="${pageContext.request.contextPath}/home" class="nav__item">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/subjectList" class="nav__item">Subject</a></li>
                    <li><a href="${pageContext.request.contextPath}/bloglist" class="nav__item">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/registrationsearch?search=" class="nav__item">Registration</a></li>
                    <li><a href="${pageContext.request.contextPath}/practicelist" class="nav__item">Practice list</a></li>
                    <li><a href="${pageContext.request.contextPath}/quizlist" class="nav__item">Quiz list</a></li>

                    <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="login nav__item" id="loginButton">Log in</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>


<c:if test="${sessionScope.account.role.roleID == 4}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="#" class="nav__item">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/slider/list" class="nav__item">Slider</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketing/bloglist?search=" class="nav__item">Blog</a></li>

                    <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="login" id="loginButton"class="nav__item">Log in</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="nav__item" id="loginButton">Login</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>



<c:if test="${sessionScope.account.role.roleID == 1}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="#" class="nav__item">Dashboard</a></li>
                    <li><a href="${pageContext.request.contextPath}/slider/list" class="nav__item">Slider</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketing/bloglist?search=" class="nav__item">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/userlist" class="nav__item">Userlist</a></li>
                    <li><a href="${pageContext.request.contextPath}/managesubject" class="nav__item">Subject</a></li>
                    <li><a href="${pageContext.request.contextPath}/test/questionlist" class="nav__item">Question</a></li>
                    <li><a href="${pageContext.request.contextPath}/quizzes" class="nav__item">Quizzes</a></li>


                    <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="login nav__item" id="loginButton">Log in</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>





<c:if test="${sessionScope.account.role.roleID == 3}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="#" class="nav__item">Dashboard</a></li>
                    <li><a href="${pageContext.request.contextPath}/sale/registrationlist" class="nav__item">Sale</a></li>

                    <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="login" id="loginButton"class="nav__item">Log in</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="nav__item" id="loginButton">Login</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>
<!--header for expert-->

<c:if test="${sessionScope.account.role.roleID == 2}">
    <header>
        <div class="header__container">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/images/logov2.png" alt="logo">
            </a>
            <nav>
                <ul class="nav__links">
                    <li><a href="#" class="nav__item">Dashboard</a></li>
                    <li><a href="${pageContext.request.contextPath}/managesubject" class="nav__item">Subject</a></li>
                    <li><a href="${pageContext.request.contextPath}/test/questionlist" class="nav__item">Question</a></li>

                    <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="login" id="loginButton"class="nav__item">Log in</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account == null}">
                        <li><a href="#" class="nav__item" id="loginButton">Login</a></li>
                        </c:if>
                        <c:if  test="${sessionScope.account != null}">
                        <li>
                            <a href="#" class="nav__item nav__item__login">Hello, ${sessionScope.user.lastName}<i class="fa fa-caret-down"></i></a>
                            <ul class="submenu">
                                <li class="submenu__item"><a href="#" id="openProfile">User Profile</a></li>
                                <li class="submenu__item"><a href="#" id="openChangePassword">Change Password</a></li>
                                <li class="submenu__item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </header>
</c:if>

