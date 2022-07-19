<%-- 
    Document   : quizhandle
    Created on : Jul 5, 2022, 4:31:28 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.ResultQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/quizhandle.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        

    </head>
    <body>

        <%
            ArrayList<ResultQuestion> rquestions = (ArrayList<ResultQuestion>) request.getSession().getAttribute("rquestions");

        %>

        <div class="top-nav">
            <div class="currentnav">
                <p><i class="fa-solid fa-question"></i> ${param.order}/<%=rquestions.size()%> </p> 
            </div>


            <div class="timer" >
                <i class="fa-solid fa-hourglass-end"></i>
                <c:choose>
                    <c:when test="${requestScope.quiz.quizType.quizTypeID == 1}">
                        <span id="timer"></span>
                    </c:when>
                    <c:when test="${requestScope.quiz.quizType.quizTypeID == 2}">
                        <label id="minutes">00</label>:<label id="seconds">00</label>
                    </c:when>
                </c:choose>
            </div>

        </div>


        <div class="body">
            <div class="question__info">
                <div>
                    <span>${param.order})</span>
                </div>
                <div>
                    <span>Question ID: ${rquestions[param.order-1].questionID}</span>
                </div>
            </div>

            <form method="POST" action="qhandle" id="answerForm"> 
                <input type="hidden" name="qhid" value="${param.qhid}">
                <input type="hidden" name="questionID" value="${rquestions[param.order-1].questionID}">
                <div class="question__body">
                    <div class="question__content">
                        ${rquestions[param.order-1].questionContent}
                    </div>

                    <div class="question__answer">
                        <c:forEach items="${rquestions[param.order-1].answers}" var="a">
                            <div class="answer__detail">
                                <input type="checkbox" name="answer" value="${a.answerID}" ${a.isChecked?"checked":""}>
                                <label for="">${a.answerContent}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="peekReview">

                    <c:if  test="${requestScope.quiz.quizType.quizTypeID == 2}">
                        <button type="button"  data-toggle="modal" data-target="#peekAnswer" class="peek__button">Peek at answer</button>
                    </c:if>
                    <div class="review">
                        <label>Mark for review</label>
                        <input class="" ${rquestions[param.order-1].isMarked?"checked":""} type="checkbox" name="isMarked" value="true">
                    </div>

                </div>
        </div>



        <div class="bot-nav">
            <div>
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Review Progress</button>
            </div>
            <div>
                <c:if test="${param.order > 1}">
                    <input type="hidden" value="qhandle?order=${param.order - 1}&qhid=${param.qhid}" name="prevLink">
                    <button type="submit" class="button-8" name="prev" value="true"> Previous </button>
                </c:if>

                <c:if test="${param.order < rquestions.size()}">
                    <input type="hidden" value="qhandle?order=${param.order + 1}&qhid=${param.qhid}" name="nextLink">
                    <button type="submit" class="button-8" name="prev" value="false">Next</button>
                </c:if>
            </div>

        </div>
    </form>


    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog modal-xl modal-dialog-centered ">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Review progress</h4>
                </div>
                <div class="modal-body">
                    <p>Review before scoring exam</p>
                    <div class="button_wrapper">
                        <div class="button__list">
                            <button value="1" id="btnUnanswered" onclick="activeButton('btnUnanswered')" class="button__filter"> <i class="fa-regular fa-square"></i>  UNANSWERED</button>
                            <button value="2" id="btnMark" onclick="activeButton('btnMark')" class="button__filter"> <i class="fa-solid fa-lightbulb"></i> MARKED</button>
                            <button value="3" id="answered" style="background-color: white;" onclick="activeButton('answered')" class="button__filter"> <i class="fa-solid fa-square"></i> ANSWERED</button>
                            <button value="4" id="btnAll" onclick="activeButton('btnAll')"  class="button__filter button__active">ALL QUESTIONS</button>
                        </div>

                        <div>
                            <button class="submit__button" data-toggle="modal" data-target="#scoreExamPopUp"">Score exam now</button>
                        </div>
                    </div>


                    <div class="question_wrapper" id="question_wrapper">
                        <c:forEach items="${rquestions}" var="r">
                            <div class="question__box" class="<c:choose><c:when test="${r.isAnswered}">answered</c:when></c:choose>" >
                                <p> ${r.isMarked?'<i class="fa-solid fa-lightbulb"></i>':""} <a href="qhandle?order=${r.order}&qhid=${param.qhid}">${r.order }</a></p>
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


    <div class="modal fade" id="scoreExamPopUp" role="dialog">
        <div class="modal-dialog modal-md modal-dialog-centered ">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Score exam?</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <c:choose>

                        <c:when test="${requestScope.totalAnswered == 0}">
                            <div>
                                <p>You have not answered any questions. Do you really wish to score this exam?</p>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.totalAnswered < requestScope.quiz.numOfQuestion}">
                            <p class="questionAnswered">
                                ${requestScope.totalAnswered} of ${requestScope.quiz.numOfQuestion} Questions Answered
                            </p>
                            <p>By clicking on the score exam button below, you will complete your current exam and receive your score. You will not be able to change any answers afther this point</p>
                        </c:when>
                        <c:when test="${requestScope.totalAnswered == requestScope.quiz.numOfQuestion}">
                            <p>By clicking on the score exam button below, you will complete your current exam and receive your score. You will not be able to change any answers afther this point</p>
                        </c:when>
                    </c:choose>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <form action="score" method="POST" id="scoreForm">
                        <button type="submit" class="submit__button" onclick="document.getElementById('answerForm').submit();">Score exam</button>
                        <input type="hidden" name="qhid" value="${param.qhid}">
                    </form>

                </div>
            </div>

        </div>
    </div>

    <!--Only show peek answer in practice mode ( quiztypeid = 2)-->
    <c:if test="${requestScope.quiz.quizType.quizTypeID == 2}">
        <div class="modal fade" id="peekAnswer" role="dialog">
            <div class="modal-dialog modal-md modal-dialog-centered ">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Peek answer</h4>
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

    </c:if>



    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/c7335013c9.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script>
// Set the date we're counting down to
                            var countDownDate = new Date("${requestScope.endTime}").getTime();

// Update the count down every 1 second
        <c:if test="${requestScope.quiz.quizType.quizTypeID == 1}">
                            var x = setInterval(function () {

                                // Get today's date and time
                                var now = new Date().getTime();

                                // Find the distance between now and the count down date
                                var distance = countDownDate - now;



                                // Time calculations for days, hours, minutes and seconds
                                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                                if (days === 0) {

                                    days = "";
                                } else
                                    days = days + "d ";


                                // Output the result in an element with id="demo"
                                document.getElementById("timer").innerHTML = days + hours + "h "
                                        + minutes + "m " + seconds + "s ";

                                // If the count down is over, write some text 
                                if (distance < 0) {
                                    clearInterval(x);
                                    document.getElementById("timer").innerHTML = "EXPIRED";
                                    document.getElementById("answerForm").submit();
                                    document.getElementById("scoreForm").submit();
                                }
                            }, 1000);
        </c:if>
        <c:if test="${requestScope.quiz.quizType.quizTypeID == 2}">
                            var minutesLabel = document.getElementById("minutes");
                            var secondsLabel = document.getElementById("seconds");
                            var totalSeconds = ${requestScope.diff};
                            setInterval(setTime, 1000);

                            function setTime() {
                                ++totalSeconds;
                                secondsLabel.innerHTML = pad(totalSeconds % 60);
                                minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));
                            }

                            function pad(val) {
                                var valString = val + "";
                                if (valString.length < 2) {
                                    return "0" + valString;
                                } else {
                                    return valString;
                                }
                            }
        </c:if>


                            $(document).on('click', '.button__filter', function (event) {
                                var categoryID = this.value;
                                $.ajax({
                                    url: "filteranswer",
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
                            
                            function activeButton(element){
                                let current = document.querySelector('.button__list .button__active');
                                current.className = current.className.replace('button__active','');
                                document.getElementById(element).className += ' button__active';
                            }
                            

                          

    </script>

</body>
</html>
