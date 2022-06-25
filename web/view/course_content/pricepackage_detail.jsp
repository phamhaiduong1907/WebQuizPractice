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
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/course_detail.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css"/>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject">Subject list</a></li>
            <li><a href="#">Subject detail</a></li>

        </ul> 

        <ul class="breadcrumb nav ">
            <li><a href="#" class="addlink headnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav">Overview</a></li>
            <li><a href="#" class="addlink headnav">Dimension</a></li>
        </ul>  

        <div class="row d-flex justify-content-center">
            <div class="col-md-10 " >
                <table>
                    <thead>
                        <tr>
                            <th>Pacakge ID</th>
                            <th>Package Name</th>
                            <th>Duration</th>
                            <th>List Price</th>
                            <th>Sale Price</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pricePackages}" var="p">
                            <tr>
                                <td>${p.pricePackageID}</td>
                                <td>${p.priceName}</td>
                                <td>${p.duration}</td>
                                <td>${p.listPrice}</td>
                                <td>${p.salePrice}</td>
                                <td>${p.status?"On":"Off"}</td>
                                <td><a href="${pageContext.request.contextPath}/pricepackage?id=${p.pricePackageID}&status=${p.status?"off":"on"}">${p.status?"Deactive":"Activate"}</a></td>
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
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "pricepackagedetail", "");</script>

    </body>
</html>
