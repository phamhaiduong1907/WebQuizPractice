<%-- 
    Document   : score
    Created on : Jul 9, 2022, 10:53:26 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/score.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <div class="score__wrapper row">
            <div class="score__content col-md-4" >
                <div class="form-group">
                    <label for="">Quiz ID: </label>
                    <input type="text" class="form-control" readonly value="${requestScope.qh.quizID.quizID}">
                </div>
                <div class="form-group">
                    <label for="">Right question: </label>
                    <input type="text" class="form-control" readonly value="${requestScope.rightQuestion}">
                </div>
                <div class="form-group">
                    <label for="">Total mark:  (Pass mark is ${requestScope.qh.quizID.passRate}) </label>
                    <input type="text" class="form-control" readonly value="${requestScope.qh.mark}">
                </div>
                <div class="form-group">
                    <label for="">Time taken </label>
                    <input type="text" class="form-control" readonly value="${requestScope.minutes} Minutes ${requestScope.seconds} Seconds">
                </div>

                <c:choose>
                    <c:when test="${requestScope.qh.mark >= requestScope.quiz.passRate}">
                        <p class="paid">Congrats you have passed the test</p>
                    </c:when>
                    <c:otherwise>
                        <p class="submitted">Better luck next time</p>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary"><a class="review_link" href="quizreview?quizHistoryID=${requestScope.qh.quizHistoryID}">Review</a></button>
                </div>    

            </div>
        </div>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

    </body>
</html>
