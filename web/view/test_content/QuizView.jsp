<%-- 
    Document   : newQuizzesList
    Created on : Jun 27, 2022, 12:26:05 AM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="mess" value="${param.mess}"/>

<!DOCTYPE html>

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
    <link href="${path}/css/test_content/quiz_view.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>
    <script>
        <c:if test="${mess != null}">
        function alertMess() {
            alert('${mess}');
        }
        </c:if>
    </script>
</head>

<body onload="alertMess();">
    <c:set value="${requestScope.quiz}" var="q"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
    <div class="content">
        <h1>Quiz Detail</h1>
        <div style="margin: 0 auto;" class="upperpart row col-md-10">
            <div class="upperpart__left" >
                <div class="form-group col-md-1">
                    <label for="">Quiz ID</label>
                    <input readonly  type="text" class="form-control" name="" value="${q.quizID}"/>
                </div>
                <div class="form-group">
                    <label for="">Quiz Name</label>
                    <input readonly  type="text" class="form-control" name="" value="${q.quizName}"/>
                </div>
                <div class="form-group">
                    <label for="">Quiz Subject</label>
                    <input readonly type="text" class="form-control" name="" value="${q.course.courseName}"/>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <label for="">Quiz Type</label>
                        <input readonly type="text" class="form-control" name="" value="${q.quizType.quizTypeName}"/>
                    </div>
                    <div class="col-md-6">
                        <label for="">Level</label>
                        <input readonly type="text" class="form-control" name="" value="${q.level.levelName}"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <label for="">Duration</label>
                        <input readonly type="text" class="form-control" name="" value="${q.duration} minnutes"/>
                    </div>
                    <div class="col-md-6">
                        <label for="">Pass Rate</label>
                        <input readonly type="text" class="form-control" name="" value="${q.passRate}%"/>
                    </div>
                </div>

                <div class="row">                
                    <div class="col-md-6">
                        <label for="">Number Of Question</label>
                        <input readonly type="text" class="form-control" name="" value="${q.numOfQuestion}"/>
                    </div>
                    <div class="col-md-6">
                        <label for="">Status</label>
                        <c:choose>
                            <c:when test= "${q.isTaken == true}">
                                <input readonly type="text" class="form-control" style="color: red;" name="" value="Uneditable"/>
                            </c:when>
                            <c:otherwise>
                                <input readonly type="text" class="form-control" style="color: green;" name="" value="Editable"/>
                            </c:otherwise>
                        </c:choose>                   
                    </div> 
                </div>
                <div class="form-group">
                    <label for="">Description:</label>
                    <textarea readonly rows="3" class="form-control" name="des">${q.description}</textarea>
                </div>
            </div>
        </div>
        <div class="btngrps">
            <a style="background-color: red;" href="../quizzes">Back</a>
            <a href="../quizzes/edit?id=${q.quizID}">Edit</a>
        </div>
    </div>   

    <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

    <script src="js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>