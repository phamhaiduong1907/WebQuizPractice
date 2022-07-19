<%-- 
    Document   : pricepackage_detail
    Created on : Jun 25, 2022, 11:05:21 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pricepackage detail</title>
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/course_detail.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/dimension_list.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/dimension_add.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
      

        <ul class="breadcrumb nav ">
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/dimension?id=${requestScope.course.courseID}" class="addlink headnav currentnav">Dimension</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}" class="addlink headnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/topiclist?id=${requestScope.course.courseID}" class="addlink headnav ">Topic</a></li>


        </ul>  

        <form method="POST" >
            <input type="hidden" name="courseID" value="${requestScope.course.courseID}">
            <div class="row d-flex justify-content-center">
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="">Dimension name:</label>
                        <input required  type="text" class="form-control" name="dimensionName" value=""/>
                    </div>
                    <div class="form-group">
                        <label for="">Dimension description:</label>
                        <input required  type="text" class="form-control" name="dimensionDescription" value=""/>
                    </div>
                    <div class="form-group">
                        <label for="">Type name:</label>
                        <select class="form-control" name="typeID">
                            <c:forEach items="${requestScope.dimensionTypes}" var="d">
                                <option value="${d.typeID}">${d.typeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" id="nav">
                        <button type="submit" class="btn btn-primary">
                            Save
                        </button>
                        <button class="btn btn-default submit">
                            <a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}">Cancel</a>
                        </button>
                    </div>

                </div>
            </div>



        </form>




        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <script>
            <c:if test="${sessionScope.message != null }">
            alert("${sessionScope.message}");
                <c:remove var="message" scope="session" />
            </c:if>

        </script>

    </body>
</html>
