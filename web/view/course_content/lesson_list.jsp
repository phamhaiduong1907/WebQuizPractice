<%-- 
    Document   : lesson_list
    Created on : Jun 23, 2022, 8:06:55 PM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson list</title>
        <link rel="stylesheet" href="css/global.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        
        <h1>Subject lessons</h1>
        
        <h4>Subject name: ${requestScope.course.courseName}</h4>
        
        <form action="lessonlist" method="POST">
            <input type="text" name="courseID" hidden value="${requestScope.course.courseID}">
            <select name="price_package" id="price_package">
                <option value="0">All price packages</option>
                <c:forEach items="${requestScope.prices}" var="p">
                    <option value="${p.pricePackageID}">${p.pricePackageID}</option>
                </c:forEach>
            </select>
            <select name="lesson_type" id="lesson_type">
                <option value="0">All lesson types</option>
                <option value="1">Subject topic</option>
                <option value="2">Lesson</option>
                <option value="3">Quiz</option>
            </select>
            <select name="lesson_status" id="lesson_status">
                <option value="All">All lesson status</option>
                <option value="1">Active</option>
                <option value="0">Inactive</option>
            </select>
            <input name="lesson_name" type="text" placeholder="Type lesson name to search">
            <input type="submit" value="Search">
        </form>
        
        <a href="#">Add lesson</a>
        
        <table>
            <tr>
                <td>ID</td>
                <td>Lesson</td>
                <td>Order</td>
                <td>Type</td>
                <td>Status</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${requestScope.lessons}" var="l">
                <tr>
                    <td>${l.lessonID}</td>
                    <td>${l.lessonName}</td>
                    <td>${l.lessonOrder}</td>
                    <td>${l.lessonType.lessonTypeName}</td>
                    <td><a href=lessonstatus?lessonID=${l.lessonID}&courseID=${requestScope.course.courseID}&page=${requestScope.page}>${l.status?"Active":"Inactive"}</a></td>
                    <td><a href="#">Edit</a><a href="#">View</a></td>
                </tr>
            </c:forEach>
        </table>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
    </body>

</html>
