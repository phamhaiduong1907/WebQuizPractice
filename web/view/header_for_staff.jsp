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
                        <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                            <c:out value="${sessionScope.account.username}"/>
                        </p>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="logout">Log out</a></li>
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
                <li><a href="${pageContext.request.contextPath}/admin/userlist">User list</a></li>


                <c:if  test="${sessionScope.account == null}">
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                    </c:if>
                    <c:if  test="${sessionScope.account != null}">
                    <li>
                        <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                            <c:out value="${sessionScope.account.username}"/>
                        </p>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="logout">Log out</a></li>
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
                        <p class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                            <c:out value="${sessionScope.account.username}"/>
                        </p>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>

    </header>
</c:if>
