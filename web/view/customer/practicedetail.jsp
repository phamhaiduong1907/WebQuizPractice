<%-- 
    Document   : practicelist
    Created on : Jul 15, 2022, 1:18:04 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="q" value="${requestScope.quiz}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sale Index</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${path}/css/admin/index.css">
        <link rel="stylesheet" href="${path}/css/popup.css">
        <link href="${path}/css/test_content/practice_detail.css" rel="stylesheet"/>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <section class="main-container">
            <!-- RIGHT CONTENT -->
            <aside class="main-content">
                <div class="right_content">
                    <h1>Practice List!</h1>
                    <form class="practiceForm" action="#" method="POST">

                        <div>
                            <label for="subject">Select Subject</label>
                            <select required <c:if test="${q != null}">disabled style="color: black"</c:if> id="subject" name="subject">
                                    <option selected disabled style="display: none"></option>
                                <c:if test="${q != null}">
                                    <option selected value="${q.quiz.course.courseID}">${q.quiz.course.courseName}</option>
                                </c:if>
                                <c:forEach items="${requestScope.courses}" var="c">
                                    <option value="${c.courseID}" <c:if test="${c.courseID == q.quiz.course.courseID}">selected</c:if>>${c.courseName}</option>
                                </c:forEach>
                            </select>
                            <c:if test="${requestScope.quiz != null}">
                                <input type="hidden" name="subject" value="${q.quiz.course.courseID}">
                            </c:if>
                        </div>
                        <div>
                            <label for="numQ">Number of practicing questions</label> <br>
                            <div id="numQ">
                                <input min="1" step="1" required <c:if test="${requestScope.quiz != null}">disabled style="color: black"</c:if> type="number" min="1" name="numOfQ" value="${q.quiz.numOfQuestion}">
                                </div>
                            </div>
                            <div>
                                <label for="">Question are selected by topic or dimension</label>
                                <select required <c:if test="${requestScope.quiz != null}">disabled style="color: black"</c:if> name="qType" id="qType">
                                <c:if test="${requestScope.quiz == null}">
                                    <option disabled style="display: none" selected></option>
                                </c:if>
                                <option value="topic" <c:if test="${q.questionType == false}"> selected</c:if>>By Topic</option>
                                <option value="dimension" <c:if test="${q.questionType == true}"> selected</c:if>>By Dimension</option>
                                </select>
                            <c:if test="${requestScope.quiz != null}">
                                <input type="hidden" name="qType" value="${q.questionType}">
                            </c:if>
                        </div>
                        <div>
                            <label>Question Group (Choose one topics/dimensions)</label>
                            <select required <c:if test="${requestScope.quiz != null}">disabled style="color: black"</c:if> name="group" id="group">
                                <c:if test="${q.questionType == false}">
                                    <option value="${q.topic.topicID}">${q.topic.topicName}<c:if test="${q.topic.topicID == 0}">All</c:if></option>
                                    <c:if test="${q.topic.topicID == null}">
                                        <option selected value="all">All</option
                                        <input type="hidden" name="group" value="all">
                                    </c:if>
                                </c:if>
                                <c:if test="${q.questionType == true}">
                                    <option value="${q.dimension.dimensionID}">${q.dimension.dimensionName}<c:if test="${q.dimension.dimensionID == 0}">All</c:if></option>
                                    <c:if test="${q.dimension.dimensionID == null}">
                                        <option selected value="all">All</option>
                                        <input type="hidden" name="group" value="all">
                                    </c:if>
                                </c:if>
                            </select> 
                            <c:if test="${requestScope.quiz != null}">
                                <c:if test="${q.questionType == false}">
                                    <input type="hidden" name="group" value="${q.topic.topicID}">
                                </c:if>
                                <c:if test="${q.questionType == true}">
                                    <input type="hidden" name="group" value="${q.dimension.dimensionID}">
                                </c:if>
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${requestScope.quiz != null}">
                                <a href="quizreview?quizHistoryID=${q.userQuizID}"></a>
                            </c:if>
                            <c:if test="${requestScope.quiz == null}">
                                <input type="submit" value="Practice">
                            </c:if>
                        </div>
                    </form>
                </div>
            </aside>
        </section>
        <footer class="footer">
            <div>FOOTER</div>
        </footer>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).on('click', '#qType', function () {
            var courseID = document.getElementById('subject').value;
            var groupName = this.value;
            $.ajax({
                url: "${path}/rendertopic",
                type: 'POST',
                dataType: 'html',
                data: {qType: groupName, ID: courseID}
            })
                    .done(function (data) {
                        $('#group').html(data);
                    })
                    .fail(function () {
                        $('#group').html("<option>Error</option>");
                    })
                    .always(function () {

                    });
        });
        $(document).on('click', '#subject', function () {
            var courseID = document.getElementById('subject').value;
            $.ajax({
                url: "${path}/rendermaxquestion",
                type: 'POST',
                dataType: 'html',
                data: {ID: courseID}
            })
                    .done(function (data) {
                        $('#numQ').html(data);
                    })
                    .fail(function () {
                        $('#numQ').html("");
                    })
                    .always(function () {

                    });
        });
    </script>
</html>
