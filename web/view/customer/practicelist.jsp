<%-- 
    Document   : practicelist
    Created on : Jul 15, 2022, 1:18:04 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
        <link href="${path}/css/test_content/practice_list.css" rel="stylesheet"/>
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
            <aside class="main-content">
                <div class="right_content">
                    <h1>Practice List!</h1>
                    <form action="searchpractice" method="GET">
                        <select name="subjectID" onchange="this.form.submit()">
                            <option selected disabled style="display: none">All subjects</option>
                            <option value="0">All subjects</option>
                            <c:forEach items="${requestScope.courses}" var="c">
                                <option value="${c.courseID}" 
                                        <c:if test="${c.courseID == requestScope.subject}">selected</c:if>
                                        >${c.courseName}</option>
                            </c:forEach>
                        </select>
                    </form>
                    <a class="newlink" href="practicedetail">New Practice</a>
                    <a class="newlink" href="quizlist">Simulation Exam</a>
                    <table id="table">
                        <c:if test="${requestScope.list.isEmpty() == true}">
                            <tr>
                                <td colspan="5">
                                    <h1 style="font-size: 50px; color: red;">NO RESULT! TAKE SOME QUIZ FIRST</h1>
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${requestScope.list}" var = "c">
                            <tr>
                                <td>
                                    ${c.quiz.course.courseName}<br>
                                    ${c.quiz.quizName}
                                </td>
                                <td>
                                    ${c.takenDate}<br>
                                    Taken Date
                                </td>
                                <td>
                                    ${c.quiz.numOfQuestion * c.mark /100} Correct <br>
                                    ${c.quiz.numOfQuestion} Questions
                                </td>
                                <td>
                                    ${c.mark}% <br>
                                    Correct
                                </td>
                                <td style="text-align: center;">
                                    <a class="detailLink" href="../SWP391-SE1617-NET_Group06-QuizWebsite/practicedetail?id=${c.userQuizID}">Detail</a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5"><span style="float: left">${c.quiz.quizType.quizTypeName}  (Level: ${c.quiz.level.levelName})</span> <span style="float: right;">Duration: ${c.duration}</span></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
                <script type="text/javascript">
                            $(document).ready(function () {
                                $("#table").fancyTable({
                                    /* Column number for initial sorting*/
                                    //undefined
                                    /* Setting pagination or enabling */
                                    pagination: true,
                                    paginationClass: 'lol',
                                    /* Rows per page kept for display */
                                    perPage: 6,
                                    searchable: false
                                });
                            });
                </script>
            </aside>
            <footer class="footer">
                <div>FOOTER</div>
            </footer>
        </section>
    </body>
</html>
