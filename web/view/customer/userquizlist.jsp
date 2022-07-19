<%-- 
    Document   : quizlist
    Created on : Jul 15, 2022, 2:20:50 AM
    Author     : Hai Tran
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="mess" value="${param.mess}"/>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simulation Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${path}/css/admin/index.css">
    <link rel="stylesheet" href="${path}/css/popup.css">
    <link href="${path}/css/customer/userquizlist.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
    <script>
        <c:if test="${mess != null}">
        function alertMess() {
            alert('${mess}');
        }
        </c:if>
    </script>
</head>

<body onload="alertMess();">

    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

    <section class="main-container">
        <!-- RIGHT CONTENT -->
        <aside class="main-content">
            <div class="right_content">
                <h1>Simulation details</h1>
                <form class="searchForm" action="#" method="POST">
                    <input list="courseList" type="text" name="course" placeholder="Choose your course">
                    <datalist id="courseList">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <option value="${c.courseName}"></option>
                        </c:forEach>
                    </datalist>
                    <input list="quizList" type="text" name="quiz" placeholder="Enter quiz name...">
                    <datalist id="quizList">
                        <c:forEach items="${requestScope.listName}" var="n">
                            <option value="${n}"></option>
                        </c:forEach>
                    </datalist>
                    <input type="submit" value="Search">
                </form>
                <table id="table">
                    <thead>
                        <tr>
                            <th data-sortas="numeric">ID</th>
                            <th>Course Name</th>                            
                            <th>Quiz</th>
                            <th>Level</th>
                            <th>Number of Question</th>
                            <th data-sortas="numeric">Duration</th>
                            <th data-sortas="numeric">Pass Rate</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${requestScope.quizzes.size() == 0}">
                            <tr>
                                <th class="error" colspan="10">No matching result!<br> Please try another keyword.</th>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </aside>
    </section>
    <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
    <script type="text/javascript">
        <c:choose>
            <c:when test="${requestScope.quizzes.size() == 0}">
    $(document).ready(function () {
        $("#table").fancyTable({
            /* Column number for initial sorting*/
//undefined
            /* Setting pagination or enabling */
            pagination: false,
            paginationClass: 'lol',
            /* Rows per page kept for display */
            perPage: 5,
            searchable: false
        });
    }
    );
            </c:when>
            <c:otherwise>
    $(document).ready(function () {
        $("#table").fancyTable({
            /* Column number for initial sorting*/
//undefined
            /* Setting pagination or enabling */
            pagination: true,
            paginationClass: 'lol',
            /* Rows per page kept for display */
            perPage: 5,
            searchable: false
        });
    }
    );
            </c:otherwise>
        </c:choose>
    </script>

    <script>
        <c:if test= "${requestScope.quizzes.size() != 0}">
        let table = document.querySelector('table tbody');
        let result = '';
            <c:forEach items="${requestScope.quizzes}" var="q">
        result +=
                `<tr>
                    <td style="font-weight: bolder;">${q.quizID}</td>
                    <td style="text-align: left;">${q.course.courseName}</td>
                    <td style="text-align: left;">${q.quizName}</td>
                    <td>${q.level.levelName}</td>
                    <td>${q.numOfQuestion}</td>
                    <td>${q.duration} mins</td>
                    <td>${q.passRate}%</td>
                    <td><a class="btn_view" href="quizzes/view?id=${q.quizID}">Details</a></td>
                    <td><a class="btn_edit" href="quizhandle?quizID=${q.quizID}"class="action__edit">Practice</a></td>
                    </tr>`;
            </c:forEach>
        table.innerHTML = result;
        </c:if>
    </script>
    <script src="${pageContext.request.contextPath}/js/customer/customerRegistration.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>