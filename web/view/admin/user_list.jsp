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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/system.css">
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
                                <div class="search__item">
                                    <input type="text" name="combination" placeholder="Type name, email or mobile to search"
                                           value="${requestScope.combination}">
                                </div>
                                <div class="search__item">
                                    <label for="roleFilter">Role</label>
                                    <select name="role" id="roleFilter">
                                        <option value="-1">All roles</option>
                                        <c:forEach items="${requestScope.roles}" var="r">
                                            <option value="${r.roleID}" ${requestScope.role == r.roleID?"selected":""}>
                                                ${r.roleName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="search__item">
                                    <label for="statusFilter">Status</label>
                                    <select name="status" id="statusFilter">
                                        <option value="all">All statuses</option>
                                        <option value="active" ${requestScope.status == "active"?"selected":""}>
                                            Active
                                        </option>
                                        <option value="inactive"${requestScope.status == "inactive"?"selected":""}>
                                            Inactive
                                        </option>
                                    </select>
                                </div>
                                <div class="search__item">
                                    <label for="genderFilter">Gender</label>
                                    <select name="gender" id="genderFilter">
                                        <option value="all">All</option>
                                        <option value="male" ${requestScope.gender == "male"?"selected":""}>
                                            Male
                                        </option>
                                        <option value="female"${requestScope.gender == "female"?"selected":""}>
                                            Female
                                        </option>
                                    </select>
                                </div>
                                <div class="search__item">
                                    <button type="submit">Search</button>
                                </div>
                            </div>
                        </form>
                        <div class="add_setting">
                            <a href="add">Add User</a>
                        </div>
                    </div>
                    <table class="setting_list user_list">
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
                </div>

                <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp" />
            </aside>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>
            $(document).ready(function () {
                $(".user_list").fancyTable({
                    pagination: true,
                    paginationClass: 'btn btn-primary',
                    perPage: 3,
                    sortColumn: 0,
                    searchable: false
                });
            });
        </script>
    </body>

</html>