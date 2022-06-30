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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <h1>Subject lessons</h1>

        <h4>Subject name: ${requestScope.course.courseName}</h4>

        <form action="searchlesson" method="GET">
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

            <a class="addlink" href="#">Add lesson</a>
        <div class="row justify-content-center">
            <div class="col-auto">
                <table class="non-scroll ">
                    <thead>
                    
                    <th>ID</th>
                    <th>Lesson</th>
                    <th>Order</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Action</th>
                   
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.lessons}" var="l">
                            <tr>
                                <td>${l.lessonID}</td>
                                <td>${l.lessonName}</td>
                                <td>${l.lessonOrder}</td>
                                <td>${l.lessonType.lessonTypeName}</td>
                                <td><a class="${l.status?"status__active":"status__inactive"}" href=lessonstatus?lessonID=${l.lessonID}&courseID=${requestScope.course.courseID}&page=${requestScope.page}>${l.status?"Active":"Inactive"}</a></td>
                                <td><a class="edit_alink" href="editlesson?lessonID=${l.lessonID}">Edit</a>
                                    <a class="view__alink" href="viewlesson?lessonID=${l.lessonID}">View</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>

        </div>

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp" />

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
    </body>

</html>
