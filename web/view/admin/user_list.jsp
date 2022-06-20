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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
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
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <section class="main">

            <aside class="right">
                <div class="right_content">
                    <div class="right_header">
                        <h1><i class="fa fa-cog"></i>User List</h1>
                    </div>
                    <div class="setting_tool">
                        <form action="userlist" method="GET">
                            <div class="search_form">
                                <div class="search_item">
                                    <select name="role">
                                        <option value="-1">All roles</option>
                                        <c:forEach items="${requestScope.roles}" var="r">
                                            <option value="${r.roleID}" ${requestScope.role == r.roleID?"selected":""}>
                                                ${r.roleName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="search_item">
                                    <select name="status">
                                        <option value="all">All statuses</option>
                                        <option value="active" ${requestScope.status == "active"?"selected":""}>
                                            Active
                                        </option>
                                        <option value="inactive"${requestScope.status == "inactive"?"selected":""}>
                                            Inactive
                                        </option>
                                    </select>
                                </div>
                                <div class="search_item">
                                    <select name="gender">
                                        <option value="all">All</option>
                                        <option value="male" ${requestScope.gender == "male"?"selected":""}>
                                            Male
                                        </option>
                                        <option value="female"${requestScope.gender == "female"?"selected":""}>
                                            Female
                                        </option>
                                    </select>
                                </div>
                                <div class="search_item">
                                    <input type="text" name="combination" placeholder="Type name, email or mobile to search"
                                           value="${requestScope.combination}">
                                </div>
                                <div class="search_item">
                                    <button type="submit">Search</button>
                                </div>
                            </div>
                            <div class="search_form search_filter">
                                <div class="search_item">
                                    <select name="sortBy">
                                        <option value="firstName" ${requestScope.sortBy == "firstName"?"selected":""}>Full Name</option>
                                        <option value="gender" ${requestScope.sortBy == "gender"?"selected":""}>Gender</option>
                                        <option value="username" ${requestScope.sortBy == "username"?"selected":""}>Email</option>
                                        <option value="phoneNumber" ${requestScope.sortBy == "phoneNumber"?"selected":""}>Mobile</option>
                                        <option value="roleID" ${requestScope.sortBy == "roleID"?"selected":""}>Role</option>
                                        <option value="status" ${requestScope.sortBy == "status"?"selected":""}>Status</option>
                                    </select>
                                </div>
                                <div class="search_item">
                                    <input type="radio" name="order" value="asc" ${requestScope.order == "asc"?"checked":""}>Ascending
                                </div>
                                <div class="search_item">
                                    <input type="radio" name="order" value="desc" ${requestScope.order == "desc"?"checked":""}>Descending
                                </div>
                            </div>
                        </form>
                        <div class="add_setting">
                            <a href="add">Add User</a>
                        </div>
                    </div>
                    <table class="setting_list">
                        <thead>
                            <tr>
                                <th>Full Name</th>
                                <th>Gender</th>
                                <th>Email</th>
                                <th>Mobile</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.users}" var="user">
                                <tr>
                                    <td>${user.lastName} ${user.firstName}</td>
                                    <td>${user.gender?"Male":"Female"}</td>
                                    <td>${user.account.username}</td>
                                    <td>${user.phoneNumber}</td>
                                    <td>${user.account.role.roleName}</td>
                                    <td>
                                        <span class="${user.status?"user__active":"user__inactive"}">${user.status?"Active":"Inactive"}</span>
                                    </td> 
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/view?username=${user.account.username}" class="link__view">View</a>
                                        <a href="${pageContext.request.contextPath}/admin/userdetail?username=${user.account.username}" class="link__edit">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination"id="pagination"></div>
                    <!--<p class="not__found">There are no results found!</p>-->
                </div>

                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>
            pagination('pagination', '<%=(url)%>',<%=(pageindex)%>, '<%=(queryString)%>', '<%=(totalpage)%>', 2);
        </script>
    </body>

</html>