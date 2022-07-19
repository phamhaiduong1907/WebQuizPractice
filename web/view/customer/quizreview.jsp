<%-- 
    Document   : quizreview
    Created on : Jul 14, 2022, 9:27:32 PM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.ResultQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Review</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/quizhandle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/quizreview.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <%
            ArrayList<ResultQuestion> rquestions = (ArrayList<ResultQuestion>) request.getSession().getAttribute("rquestions");

        %>

        <div class="top-nav">
            <div class="currentnav">
                <p><i class="fa-solid fa-question"></i> ${param.order}/<%=rquestions.size()%> </p> 
            </div>
        </div>


        <div class="body" style="font-size: 16px;">
            <div class="question__info">
                <div>
                    <span>${param.order})</span>
                </div>
                <div>
                    <span>Question ID: ${rquestions[param.order-1].questionID}</span>
                </div>
            </div>

            <div class="question__body">
                <div class="question__content">
                    ${rquestions[param.order-1].questionContent}
                </div>

                <div class="question__answer">
                    <c:forEach items="${rquestions[param.order-1].answers}" var="a">
                        <div class="answer__detail ${a.isChecked?'':'non__choice'}" >
                            ${a.isChecked?'<div class="pointer">Your answer</div>':''}
                            <div class="${a.isTrue?"answerright__option":"answerwrong__option"}"> ${a.isTrue?'<i class="fa-solid fa-check"></i>':'<i class="fa-solid fa-xmark"></i>'} </div>
                            <label for="">${a.answerContent}</label>
                        </div>
                    </c:forEach>
                </div>


            </div>

            <div class="peekReview">
                <button type="button"  data-toggle="modal" data-target="#peekAnswer" class="peek__button">Explanation</button>
            </div>
        </div>

        <div class="bot-nav">
            <div>
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Review Progress</button>
            </div>
            <div>
                <c:if test="${param.order > 1}">
                    <a href="qreview?order=${param.order - 1}&qhid=${param.qhid}"><button type="submit" class="button-8" name="prev" value="true"> Previous </button></a>
                </c:if>
                <c:if test="${param.order < rquestions.size()}">
                    <a href="qreview?order=${param.order + 1}&qhid=${param.qhid}"><button type="submit" class="button-8" name="prev" value="false">Next</button></a>
                </c:if>
            </div>

        </div>


        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog modal-xl modal-dialog-centered ">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Review progress</h4>
                    </div>
                    <div class="modal-body">
                        <div class="button_wrapper">
                            <div class="button__list">
                                <button value="1" id="btnUnanswered" onclick="activeButton('btnUnanswered')" class="button__filter"> <i class="fa-regular fa-square"></i>  UNANSWERED</button>
                                <button value="2" id="btnMark" onclick="activeButton('btnMark')" class="button__filter"> <i class="fa-solid fa-lightbulb"></i> MARKED</button>
                                <button value="3" id="answered" style="background-color: white;" onclick="activeButton('answered')" class="button__filter"> <i class="fa-solid fa-square"></i> ANSWERED</button>
                                <button value="4" id="btnAll" onclick="activeButton('btnAll')"  class="button__filter button__active">ALL QUESTIONS</button>
                            </div>
                        </div>


                        <div class="question_wrapper" id="question_wrapper">
                            <c:forEach items="${rquestions}" var="r">
                                <div class="question__box" id="<c:choose><c:when test="${r.isAnswered}">answered</c:when></c:choose>" >
                                    <p> ${r.isMarked?'<i class="fa-solid fa-lightbulb"></i>':""} <a href="qreview?order=${r.order}&qhid=${param.qhid}">${r.order }</a></p>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <div class="modal fade" id="peekAnswer" role="dialog">
            <div class="modal-dialog modal-md modal-dialog-centered ">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Explanation</h4>
                    </div>
                    <div class="modal-body">
                        <div>

                            <p>${rquestions[param.order-1].explanation}</p>

                            <c:if test="${rquestions[param.order-1].explanation == null}">
                                No explanation
                            </c:if>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>


        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>



        <script>
            $(document).on('click', '.button__filter', function (event) {
                var categoryID = this.value;
                $.ajax({
                    url: "reviewfilter",
                    type: 'POST',
                    dataType: 'html',
                    data: {ID: categoryID, qhid: ${param.qhid}, quizID:${requestScope.quiz.quizID}},
                })
                        .done(function (data) {
                            $('#question_wrapper').html(data);
                        })
                        .fail(function (error) {
                            $('#question_wrapper').html("<h1>error</h1>");
                        })
                        .always(function () {

                        });

            });


            function activeButton(element) {
                let current = document.querySelector('.button__list .button__active');
                current.className = current.className.replace('button__active', '');
                document.getElementById(element).className += ' button__active';
            }
        </script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/c7335013c9.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>
