<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Feature"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/user_detail.css">
        <%
            Role roleById = (Role) request.getAttribute("roleById");
            User user = (User) request.getAttribute("user");
        %>
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <section class="main"> 
            <aside class="right">
                <div class="right_content">
                    <h1>User Details</h1>
                    <form action="userdetail" method="POST">
                        <div class="personal_info">
                            <div class="info">
                                <div class="info_item">
                                    <label for="name">First Name</label>
                                    <input type="text" id="firstNameInput" name="firstName" placeholder="Enter your first name"
                                           value="${requestScope.user.firstName}" readonly>
                                </div>
                                <div class="info_item">
                                    <label for="name">Last Name</label>
                                    <input type="text" id="lastNameInput" name="lastName" placeholder="Enter your last name"
                                           value="${requestScope.user.lastName}" readonly>
                                </div>
                                <div class="info_item">
                                    <label for="email">Email</label>
                                    <input type="text" id="emailInput" name="email" placeholder="abc@gmail.com"
                                           value="${requestScope.user.account.username}" readonly>
                                </div>
                                <div class="info_item">
                                    <label for="mobile">Mobile</label>
                                    <input type="text" id="mobileInput" name="phone" placeholder="Mobile"
                                           value="${requestScope.user.phoneNumber}" readonly>
                                </div>
                                <div class="info_item">
                                    <label for="gender">Gender</label>
                                    <input type="radio" name="gender" value="male" ${requestScope.user.gender ? "checked" : ""} disabled>Male
                                    <input type="radio" name="gender" value="female" ${requestScope.user.gender ? "" : "checked"} disabled>Female
                                </div>
                            </div>
                            <div class="avatar">
                                <img src="${pageContext.request.contextPath}/${requestScope.user.profilePictureUrl}" alt="alt"/>
                            </div>
                        </div>
                        <div class="authorize_info">
                            <div class="authorize_item">
                                <label for="role">Role</label>
                                <select name="roleID" id="roleSelect" disabled>
                                    <c:forEach items="${requestScope.roles}" var="role">
                                        <option value="${role.roleID}" 
                                                ${role.roleID == requestScope.user.account.role.roleID ?"selected":""}
                                                >
                                            ${role.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <div class="authorize_item">
                                <label for="status">Status</label>
                                <select name="status" id="statusInput" disabled>
                                    <option value="active" ${requestScope.user.status?"selected":""}>active</option>
                                    <option value="inactive" ${requestScope.user.status?"":"selected"}>inactive</option>
                                </select>
                            </div> 
                            <div class="authorize_item authorize_address">
                                <label for="address">Address</label>
                                <input type="text" name="address" id="addressInput" placeholder="Enter your adress"
                                       value="${requestScope.user.address}" readonly/>
                            </div> 
                        </div>
                    </form>
                    <p id="confirmMessage" style="text-align: center; color: #1fe21e; margin-top: 10px;">${param.confirm}</p>
                </div>
            </aside>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>                    
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    </body>
</html>