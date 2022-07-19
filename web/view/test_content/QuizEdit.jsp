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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
    crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Bootstrap's CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/addquestion.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>

<body>
    <c:set value="${requestScope.quiz}" var="q"/>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
    <div class="content">
        <h1>Quiz Detail Editor</h1>
        <div style="margin: 0 auto;" class="tab col-md-10">
            <button id="openS"  class="tablinks active" onclick="openTab('overview', 'setting'); changeActive('openO', 'openS');">Settings</button>
            <button id="openO" class="tablinks" onclick="openTab('setting', 'overview'); changeActive('openS', 'openO');">Overview</button>
        </div>
        <div class="upperpart row">
            <div style="margin: 0 auto;" class="col-md-10" >
                <form id="myForm" action="../quizzes/edit" method="POST">
                    <div id="setting" style="display: block">
                        <div class="row">
                            <div class="col-md-8">
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
                            <div class="col-md-4">
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
                            <div class="col-md-6">
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
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <label for="">Subject</label>
                                <select required id="courseID" name="courseID">
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
                    </div>
                    <div id="overview" style="display: none">
                        <c:if test="${q != null}">
                            <div class="form-group col-md-1">
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
                                <label for="">Duration (mins)</label>
                                <input min="1" required type="number" class="form-control" name="duration" value="${q.duration}"/>
                            </div>
                            <div class="col-md-4">
                                <label for="">Pass Rate (%)</label>
                                <input min="1" required type="number" class="form-control" name="passRate" value="${q.passRate}"/>
                            </div>
                            <div class="col-md-4">
                                <label for="">Number Of Question</label>
                                <div id="numQ">
                                    <input min="1" step="1" required type="number" class="form-control" name="numOfQ" value="${q.numOfQuestion}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label for="">Description</label>
                                <textarea required rows="3"  class="form-control" name="des">${q.description}</textarea>
                            </div>
                            <div class="col-md-4">
                                <label for="">Note</label>
                                <textarea required rows="3"  class="form-control" name="note">${q.note}</textarea>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="form-group btngrps">
                    <a style="background-color: #fff; color: #000;" href="../quizzes" onclick="conf('You are about to cancel!?');">Cancel</a>
                    <input style="border-radius: 5px; padding: 7px;" form="myForm" onclick="conf('You are about to save this!?');" type="submit" value="Save">
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                        $(document).on('click', '#cID', function () {
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
                                    
                                    .always(function () {

                                    });
                        });

                        $(document).on('click', '#scID', function () {
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
                                   
                                    .always(function () {

                                    });
                        });

                        $(document).on('click', '#courseID', function () {
                            var courseID = this.value;
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
        <script>
            function openTab(from, to) {
                var fromTab = document.getElementById(from);
                fromTab.style.display = "none";
                var toTab = document.getElementById(to);
                toTab.style.display = "block";
            }

            function changeActive(from, to) {
                var fromTab = document.getElementById(from);
                fromTab.classList.remove("active");
                var toTab = document.getElementById(to);
                toTab.classList.add("active");
            }
        </script>
        <script>
            function conf(mess) {
                var sure = window.confirm(mess);
                if (sure === false) {
                    event.preventDefault();
                }
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