<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User list</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/system.css">
        <%
            int pageindex = (Integer) request.getAttribute("pageindex");
            int totalpage = (Integer) request.getAttribute("totalpage");
            String url = (String) request.getAttribute("url");
            String queryString = (String) request.getAttribute("queryString");
            int count = (Integer) request.getAttribute("count");
        %>
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
                            <li><a href="../logout">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <section class="main">
            <aside class="left">
                <nav>
                    <ul>
                        <c:forEach items="${sessionScope.account.role.features}" var="f">
                            <c:if test="${f.isDisplayed == true}">
                                <li><a href="${f.url}">${f.featureName}</a></li>
                                </c:if>                            
                            </c:forEach>
                    </ul>
                </nav>
                 <nav>
                    <ul>
                        <li><a href="../marketing/bloglist?search=">Posts</a></li>
                        <li><a href="../slider/list">Sliders</a></li>
                        <li><a href="../admin/userlist">Users</a></li>
                        <li><a href="../managesubject">Course</a></li>
                    </ul>
                </nav>
            </aside>
            <aside class="right">
                <div class="right_content">
                    <div class="right_header">
                        <h1><i class="fa fa-cog"></i>User List</h1>
                    </div>
                    <div class="setting_tool">
                        <div class="search_form">
                            <form action="userlist" method="GET">
                                <select name="role">
                                    <option value="-1">All roles</option>
                                    <c:forEach items="${requestScope.roles}" var="r">
                                        <option value="${r.roleID}" ${requestScope.role == r.roleID?"selected":""}>
                                            ${r.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <select name="status">
                                    <option value="all">All statuses</option>
                                    <option value="active" ${requestScope.status == "active"?"selected":""}>
                                        Active
                                    </option>
                                    <option value="inactive"${requestScope.status == "inactive"?"selected":""}>
                                        Inactive
                                    </option>
                                </select>
                                <select name="gender">
                                    <option value="all">All</option>
                                    <option value="male" ${requestScope.gender == "male"?"selected":""}>
                                        Male
                                    </option>
                                    <option value="female"${requestScope.gender == "female"?"selected":""}>
                                        Female
                                    </option>
                                </select>
                                <input type="text" name="combination" placeholder="Type name, email or mobile to search"
                                       value="${requestScope.combination}">
                                <select name="sortBy">
                                    <option value="firstName" ${requestScope.sortBy == "firstName"?"selected":""}>Full Name</option>
                                    <option value="gender" ${requestScope.sortBy == "gender"?"selected":""}>Gender</option>
                                    <option value="username" ${requestScope.sortBy == "username"?"selected":""}>Email</option>
                                    <option value="phoneNumber" ${requestScope.sortBy == "phoneNumber"?"selected":""}>Mobile</option>
                                    <option value="roleID" ${requestScope.sortBy == "roleID"?"selected":""}>Role</option>
                                    <option value="status" ${requestScope.sortBy == "status"?"selected":""}>Status</option>
                                </select>
                                <input type="radio" name="order" value="asc" ${requestScope.order == "asc"?"checked":""}>Ascending
                                <input type="radio" name="order" value="desc" ${requestScope.order == "desc"?"checked":""}>Descending
                                <button type="submit">Search</button>
                            </form>
                        </div>
                        <div class="add_setting">
                            <a href="add">Add User</a>
                        </div>
                    </div>
                    <table class="setting_list">
                        <tr>
                            <td>Full Name</td>
                            <td>Gender</td>
                            <td>Email</td>
                            <td>Mobile</td>
                            <td>Role</td>
                            <td>Status</td>
                            <td>Action</td>
                        </tr>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr>
                                <td>${user.lastName} ${user.firstName}</td>
                                <td>${user.gender?"Male":"Female"}</td>
                                <td>${user.account.username}</td>
                                <td>${user.phoneNumber}</td>
                                <td>${user.account.role.roleName}</td>
                                <td>Active</td> 
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/userdetail?username=${user.account.username}">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="pagination"id="pagination"></div>
                    <!--<p class="not__found">There are no results found!</p>-->
                </div>

                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

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

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>
            pagination('pagination','<%=(url)%>',<%=(pageindex)%>,'<%=(queryString)%>','<%=(totalpage)%>',2);
        </script>
    </body>

</html>