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
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <section class="main">

            <aside class="right">
                <div class="right_content">
                    <h1>Add User</h1>
                    <form action="add" method="POST" onsubmit="return checkSubmit()">
                        <div class="personal_info">
                            <div class="info">
                                <div class="info_item">
                                    <label for="name">First Name</label>
                                    <input type="text" id="firstNameInput" name="firstName" required>
                                </div>
                                <div class="info_item">
                                    <label for="name">Last Name</label>
                                    <input type="text" id="lastNameInput" name="lastName" required>
                                </div>
                                <div class="info_item">
                                    <label for="email">Email</label>
                                    <input type="text" id="emailInput" name="email" required>
                                </div>
                                <div class="info_item">
                                    <label for="mobile">Mobile</label>
                                    <input type="text" id="mobileInput" name="phone" required>
                                </div>
                                <div class="info_item">
                                    <label for="gender">Gender</label>
                                    <input type="radio" name="gender" value="male" checked>Male
                                    <input type="radio" name="gender" value="female">Female
                                </div>
                            </div>
                            <div class="avatar">
                                <img src="${pageContext.request.contextPath}/images/default_user_avatar.png" alt="alt"/>
                            </div>
                        </div>
                        <div class="authorize_info">
                            <div class="authorize_item">
                                <label for="role">Role</label>
                                <select name="roleID" id="roleSelect">
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
                                <select name="status" id="statusInput">
                                    <option value="active">active</option>
                                    <option value="inactive">inactive</option>
                                </select>
                            </div> 
                            <div class="authorize_item authorize_address">
                                <label for="address">Address</label>
                                <input type="text" name="address" id="addressInput" required>
                            </div> 
                        </div>
                        <button type="submit">Save</button>
                    </form>
                                <p style="text-align: center; color: #ff0000;" id="warning">${requestScope.error}</p>
                </div>
            </aside>
        </section>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>                    
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script>
                        function checkSubmit() {
                            var emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
                            var phoneRegex = /^0[0-9]{9,10}$/;
                            var email = document.getElementById('emailInput').value.trim();
                            var phone = document.getElementById('mobileInput').value.trim();
                            if (!emailRegex.test(email)) {
                                alert('Please check your email again!');
                                console.log("check email");
                                return false;
                            } else if (!phoneRegex.test(phone)) {
                                alert('Please check your phone again!');
                                console.log("check phone");
                                return false;
                            } else {
                                return true;
                            }
                        }
                        
                        var inputs = document.getElementsByTagName('input');
                        for(let i = 0; i < inputs.length; i++){
                            inputs[i].addEventListener('focus', function (){
                                document.getElementById('warning').remove();
                            });
                        }
        </script>
    </body>
</html>
