<%-- 
    Document   : viewlesson
    Created on : Jun 29, 2022, 3:16:56 PM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Lesson</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/lessondetail.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <section class="main">
            <h1>Lesson Details</h1>

            <h4>Subject name: ${requestScope.course.courseName}</h4>

            <form>

                <label for="lessonName">Lesson Name:</label>
                <input type="text" name="lessonName" disabled value="${requestScope.lesson.lessonName}">
                <label for="lessonType">Lesson Type:</label>
                <select name="lessonType" disabled>
                    <c:forEach var="ltype" items="${requestScope.lessonTypes}">
                        <option ${(ltype.lessonTypeID==requestScope.lesson.lessonType.lessonTypeID)?"selected=\"selected\"":""} value="${ltype.lessonTypeID}">
                            ${ltype.lessonTypeName}
                        </option>
                    </c:forEach>
                </select>
                <label for="topic">Lesson Topic:</label>
                <select name="topic" disabled>
                    <option>
                        <c:forEach var="t" items="${requestScope.topics}">
                        <option ${(t.topicID==requestScope.lesson.topicID)?"selected=\"selected\"":""} value="${t.topicID}">
                            ${t.topicName}
                        </option>
                    </c:forEach>
                    </option>
                </select>
                <label for="lessonOrder">Order:</label>
                <input type="number" name="lessonOrder" value="${requestScope.lesson.lessonOrder}" disabled>
                <c:choose>
                    <c:when test="${requestScope.lesson.lessonType.lessonTypeID == 2}">
                        <div>
                            <label for="videoLink">Video Link:</label>
                            <input type="text" name="videoLink" value="${requestScope.lesson.videoLink}" placeholder="Youtube link.." disabled>
                        </div>
                        <div>
                            <label for="htmlContent">HTML Content:</label>
                            <textarea name="htmlContent" readonly>
                                ${requestScope.lesson.htmlContent}
                            </textarea>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.lesson.lessonType.lessonTypeID == 3}">
                        <div>
                            <label for="quiz">Choose Quiz:</label>
                            <select name="quiz" disabled>
                                <c:forEach var="q" items="${requestScope.quizzes}">
                                    <option ${(q.quizID==requestScope.lesson.quizID)?"selected=\"selected\"":""} value="${q.quizID}">
                                        ${q.quizName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="htmlContent">HTML Content:</label>
                            <textarea name="htmlContent" readonly>
                                ${requestScope.lesson.htmlContent}
                            </textarea>
                        </div>
                    </c:when>
                </c:choose>
            </form>
            <a href="editlesson?lessonID=${requestScope.lesson.lessonID}&courseID=${requestScope.course.courseID}"><button>Edit</button></a>
            <div class="mess">
                <p>${sessionScope.mess}</p>
            </div>

            <c:remove scope="session" var="mess"></c:remove>
            </section>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
    </body>
</html>
