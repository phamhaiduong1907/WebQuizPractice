<%-- 
    Document   : newQuizzesList
    Created on : Jun 27, 2022, 12:26:05 AM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

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
</head>

<body>
    <c:set value="${requestScope.quiz}" var="q"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
    <div class="content">
        <h1>Quiz Detail Editor</h1>
        <div class="upperpart row">

            <div class="upperpart__left col-md-6" >
                <form action="../quizzes/edit"method="POST">
                    <c:if test="${q != null}">
                        <div class="form-group">
                            <label for="">Quiz ID</label>
                            <input readonly  type="text" class="form-control" name="ID" value="${q.quizID}"/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="name">Quiz Name</label>
                        <input required type="text" class="form-control" name="name" value="${q.quizName}"/>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label for="">Category</label>
                            <select required id="cID">
                                <c:if test="${q == null}">
                                    <option selected disabled style="display: none;">Select Category</option>
                                </c:if>
                                <c:forEach items="${requestScope.cates}" var="c">
                                    <option
                                        <c:if test="${c.categoryID == q.course.subcategory.categoryID}">selected</c:if>
                                        value="${c.categoryID}">${c.categoryName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="">SubCategory</label>
                            <select required id="scID">
                                <c:choose>
                                    <c:when test="${q != null}">
                                        <option selected value="${q.course.subcategory.subcategoryID}">${q.course.subcategory.subcategoryName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected disabled style="display: none;">Select SubCategory</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="">Subject</label>
                            <select required id="courseID" name="courseID" onchange="renderThumbnail()">
                                <c:choose>
                                    <c:when test="${q != null}">
                                        <option selected value="${q.course.courseID}">${q.course.courseName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected disabled style="display: none;">Select Subject</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="">Quiz Type</label>
                            <select required name="type">
                                <c:if test="${q == null}">
                                    <option selected disabled style="display: none;">Select Quiz Type</option>
                                </c:if>
                                <c:forEach items="${requestScope.types}" var="t">
                                    <option
                                        <c:if test="${q.quizType.quizTypeID == t.quizTypeID}">selected</c:if>
                                        value="${t.quizTypeID}">${t.quizTypeName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="">Level</label>
                            <select required name="level">
                                <c:forEach items="${requestScope.levels}" var="l">
                                    <option
                                        <c:if test="${q.level.levelID == l.levelID}">selected</c:if>
                                        value="${l.levelID}">${l.levelName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <label for="">Duration (mins)</label>
                            <input required type="number" class="form-control" name="duration" value="${q.duration}"/>
                        </div>
                        <div class="col-md-6">
                            <label for="">Pass Rate (%)</label>
                            <input required type="number" class="form-control" name="passRate" value="${q.passRate}"/>
                        </div>
                    </div>
                    <div class="row">                
                        <div class="col-md-6">
                            <label for="">Number Of Question</label>
                            <input required type="number" class="form-control" name="numOfQ" value="${q.numOfQuestion}"/>
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
                        <textarea required style="height: 65px;"  class="form-control" name="des">${q.description}</textarea>
                    </div>
                    <div class="btngrps">
                        <input type="reset" value="Reset">
                        <input type="submit" value="Save">
                    </div>
                </form>
            </div>

            <div class="col-md-6" >
                <img style="margin-top: 100px;" src="${path}/images/thumbnails/${q.course.thumbnailUrl}">
            </div>  

        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).on('change', '#cID', function () {
                var cateID = this.value;
                $.ajax({
                    url: "${path}/rendersubcate",
                    type: 'POST',
                    dataType: 'html',
                    data: {ID: cateID}
                })
                        .done(function (data) {
                            $('#scID').html(data);
                        })
                        .fail(function () {
                            $('#scID').html("<option>Error</option>");
                        })
                        .always(function () {

                        });
            });

            $(document).on('change', '#scID', function () {
                var subcateID = this.value;
                $.ajax({
                    url: "${path}/rendercourse",
                    type: 'POST',
                    dataType: 'html',
                    data: {ID: subcateID}
                })
                        .done(function (data) {
                            $('#courseID').html(data);
                        })
                        .fail(function () {
                            $('#courseID').html("<option>Error</option>");
                        })
                        .always(function () {

                        });
            });
            
        </script>
        <script>
            function renderThumbnail() {
                var ID = document.getElementById("courseID").value;
                console.log(ID);
            }
        </script>
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