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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">

<!--Header for guest -->
<c:if test="${sessionScope.account == null}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/subjectList">Subject</a></li>
                <li><a href="${pageContext.request.contextPath}/bloglist">Blog</a></li>

                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton" style="pointer-events: auto; line-height: 90px !important;">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <p class="login " id="loginButton"><i class="fa fa-user-alt"></i>
                            <c:out value="${sessionScope.account.username}"/>
                        </p>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>


<!--Cusomter header when logged in-->
<c:if test="${sessionScope.account.role.roleID == 5}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/subjectList">Subject</a></li>
                <li><a href="${pageContext.request.contextPath}/bloglist">Blog</a></li>
                <li><a href="${pageContext.request.contextPath}/registrationsearch?search=">Registration</a></li>

                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <div class="welcome__user">
                            <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                                <c:out value="${sessionScope.account.username}"/>
                            </p>
                        </div>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>


<c:if test="${sessionScope.account.role.roleID == 4}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="#">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/slider/list">Slider</a></li>
                <li><a href="${pageContext.request.contextPath}/marketing/bloglist?search=">Blog</a></li>


                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <div class="welcome__user">
                            <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                                <c:out value="${sessionScope.account.username}"/>
                            </p>
                        </div>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>

<c:if test="${sessionScope.account.role.roleID == 1}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="#">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/slider/list">Slider</a></li>
                <li><a href="${pageContext.request.contextPath}/marketing/bloglist?search=">Blog</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/userlist">Userlist</a></li>
                <li><a href="${pageContext.request.contextPath}/managesubject">Subject</a></li>


                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <div class="welcome__user">
                            <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                                <c:out value="${sessionScope.account.username}"/>
                            </p>
                        </div>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>

<c:if test="${sessionScope.account.role.roleID == 3}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="#">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/sale/registrationlist">Sale</a></li>



                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <div class="welcome__user">
                            <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                                <c:out value="${sessionScope.account.username}"/>
                            </p>
                        </div>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>
<!--header for expert-->
<c:if test="${sessionScope.account.role.roleID == 2}">
    <header>
        <div class="heading_logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="alt"/>
        </div>
        <nav>
            <ul class="nav_links">
                <li><a href="#">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/managesubject">Subject</a></li>



                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <div class="welcome__user">
                            <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                                <c:out value="${sessionScope.account.username}"/>
                            </p>
                        </div>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>
