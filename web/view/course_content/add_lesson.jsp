<%-- 
    Document   : add_lesson
    Created on : Jun 30, 2022, 4:51:19 PM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Lesson</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><!-- comment -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/lessondetail.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <section class="main">
            <h1>Lesson Details</h1>

            <h4>Subject name: ${requestScope.course.courseName}</h4>

            <form action="addlesson" method="POST">
                <div>
                    <input name="courseID" hidden value="${requestScope.course.courseID}">
                </div>
                <label for="lessonName">Lesson Name:</label>
                <input type="text" required name="lessonName">
                <label for="lessonType">Lesson Type:</label>
                <select name="lessonType" id="lessonType">
                    <c:forEach var="ltype" items="${requestScope.lessonTypes}">
                        <option value="${ltype.lessonTypeID}">
                            ${ltype.lessonTypeName}
                        </option>
                    </c:forEach>
                </select>
                <label for="topic">Lesson Topic:</label>
                <select name="topic">
                    <c:forEach var="t" items="${requestScope.topics}">
                        <option value="${t.topicID}">
                            ${t.topicName}
                        </option>
                    </c:forEach>
                </select>
                <label for="lessonOrder">Order:</label>
                <input type="number" name="lessonOrder" min="1" required>
                    <div id="videoDiv" class="videoDiv" style="display: none">
                    <label for="videoLink">Video Link:</label>
                    <input type="text" name="videoLink" placeholder="Youtube link..">
                </div>
                <div id="htmlDiv" class="htmlDiv" style="display: none">
                    <label for="htmlContent">HTML Content:</label>
                    <textarea name="htmlContent"></textarea>
                </div>
                <div id="quizDiv" class="quizDiv" style="display: none">
                    <label for="quiz">Choose Quiz:</label>
                    <select name="quiz">
                        <c:forEach var="q" items="${requestScope.quizzes}">
                            <option value="${q.quizID}">
                                ${q.quizName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="Add">
            </form>
            <p>${sessionScope.mess}</p>
            <c:remove scope="session" var="mess"></c:remove>
            </section>
            <script src="${pageContext.request.contextPath}/js/course_content/lessondetail.js"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
    </body>
</html>
