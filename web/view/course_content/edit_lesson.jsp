<%-- 
    Document   : edit_lesson
    Created on : Jun 29, 2022, 8:59:30 PM
    Author     : Zuys
--%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Edit Lesson</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/lessondetail.css">
            </head>

            <body>
                <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp" />

                <section class="main">
                    <h1>Lesson Details</h1>

                    <h4>Subject name: ${requestScope.course.courseName}</h4>

                    <form action="editlesson" method="POST">
                        <div>
                            <input name="lessonID" type="hidden" value="${requestScope.lesson.lessonID}">
                            <input name="courseID" type="hidden" value="${requestScope.course.courseID}">
                        </div>
                        <label for="lessonName">Lesson Name:</label>
                        <input type="text" required name="lessonName" value="${requestScope.lesson.lessonName}">
                        <label for="lessonType">Lesson Type:</label>
                        <select name="lessonType" id="lessonType">
                    <c:forEach var="ltype" items="${requestScope.lessonTypes}">
                        <option ${(ltype.lessonTypeID==requestScope.lesson.lessonType.lessonTypeID)?"selected=\"selected\"":""} value="${ltype.lessonTypeID}">
                            ${ltype.lessonTypeName}
                        </option>
                    </c:forEach>
                </select>
                        <label for="topic">Lesson Topic:</label>
                        <select name="topic">
                    <c:forEach var="t" items="${requestScope.topics}">
                        <option ${(t.topicID==requestScope.lesson.topicID)?"selected=\"selected\"":""} value="${t.topicID}">
                            ${t.topicName}
                        </option>
                    </c:forEach>
                </select>
                        <label for="lessonOrder">Order:</label>
                        <input type="number" name="lessonOrder" required min="1" value="${requestScope.lesson.lessonOrder}">
                        <div id="videoDiv" class="videoDiv" <c:if test="${requestScope.lesson.lessonType.lessonTypeID==1}">
                            style="display: none"
                            </c:if>>
                            <label for="videoLink">Video Link:</label>
                            <input type="text" name="videoLink" value="${requestScope.lesson.videoLink}" placeholder="Youtube link..">
                        </div>
                        <div id="htmlDiv" class="htmlDiv" <c:if test="${requestScope.lesson.lessonType.lessonTypeID==1}">
                            style="display: none"
                            </c:if>>
                            <label for="htmlContent">HTML Content:</label>
                            <textarea name="htmlContent">
                        ${requestScope.lesson.htmlContent}
                    </textarea>
                        </div>
                        <div id="quizDiv" class="quizDiv" <c:if test="${requestScope.lesson.lessonType.lessonTypeID==1}">
                            style="display: none"
                            </c:if>>
                            <label for="quiz">Choose Quiz:</label>
                            <select name="quiz">
                        <c:forEach var="q" items="${requestScope.quizzes}">
                            <option ${(q.quizID==requestScope.lesson.quizID)?"selected=\"selected\"":""} value="${q.quizID}">
                                ${q.quizName}
                            </option>
                        </c:forEach>
                    </select>
                        </div>
                        <input type="submit" value="Save">
                    </form>
                </section>

                <script src="${pageContext.request.contextPath}/js/course_content/lessondetail.js" />
                <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
            </body>

            </html>