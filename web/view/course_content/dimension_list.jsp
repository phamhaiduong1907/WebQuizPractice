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
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/dimension?id=${requestScope.course.courseID}" class="addlink headnav currentnav" >Dimension</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}" class="addlink headnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/topiclist?id=${requestScope.course.courseID}" class="addlink headnav ">Topic</a></li>


        </ul>  

        <a class="addlink_ver2" href="dimensionadd?id=${requestScope.course.courseID}">Add dimension</a>

        <div class=" d-flex justify-content-center table__content">
            <div class="" >
                <table>
                    <thead>
                        <tr>
                            <th>Dimension ID</th>
                            <th>Type</th>
                            <th>Dimension</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.dimensions}" var="d">
                            <tr>
                                <td>${d.dimensionID}</td>
                                <td>${d.dimensionType.typeName}</td>
                                <td>${d.dimensionName}</td>
                                <td>${d.dimensionDescription}</td>

                                <td><a href="dimensionedit?did=${d.dimensionID}&cid=${requestScope.course.courseID}" class="edit_alink">Edit</a>    <a class="delete_alink" href="${pageContext.request.contextPath}/managesubject/subjectdetail/deletedimension?courseID=${requestScope.course.courseID}&dimensionID=${d.dimensionID}" onclick="return confirm('Are you sure you want to delete?')">Delete</a>
                                    
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination" id="pagination">

                </div>
            </div>
        </div>




        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "dimension", "${requestScope.queryString}");

            <c:if test="${sessionScope.errormessage != null}">

                                    alert("${sessionScope.errormessage}");
                <c:remove var="errormessage" scope="session"/>
            </c:if>

        </script>

    </body>
</html>
